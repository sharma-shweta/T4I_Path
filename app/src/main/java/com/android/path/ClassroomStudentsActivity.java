package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.path.models.Student;
import com.android.path.utils.FirebaseAPI;
import com.android.path.utils.SharedPreferencesAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class ClassroomStudentsActivity extends AppCompatActivity {

    public static Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_students);
        bus = new Bus(ThreadEnforcer.ANY);
        bus.register(this);
        populateNoOfStudent();
    }

    private void populateNoOfStudent() {
        ValueEventListener studentList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer count = 0;
                String classId = SharedPreferencesAPI.get(ClassroomStudentsActivity.this, getString(R.string.classSharedPref), "");
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Student s = d.getValue(Student.class);
                    if (s.currentClass != null && s.currentClass.equals(classId))
                        ++count;
                }
                bus.post(count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ClassRoomStudentsAct", "locationList:onCancelled", databaseError.toException());
            }
        };
        FirebaseAPI.getStudentsDBRef().addListenerForSingleValueEvent(studentList);
    }

    @Subscribe
    public void nextMethod(Integer noOfStudents) {
        boolean classHasStudents = noOfStudents != 0;
        Log.d("ClassroomStudentsAct", "Received noOfStudents=" + noOfStudents);
        if (classHasStudents) {
            Button view_students = (Button) findViewById(R.id.btnViewClassroomStudents);
            view_students.setVisibility(View.VISIBLE);
            ImageButton add_students = (ImageButton) findViewById(R.id.btnAddStudents);
            add_students.setVisibility(View.INVISIBLE);
            TextView classroom_msg = (TextView) findViewById(R.id.addStudentMsg);
            String message = "We have found " + noOfStudents + " students in your class.";
            classroom_msg.setText(message);
        }
    }

    public void gotoAddStudent(View view) {
        Log.d("ClassroomStudentsAct", "Starting AddStudentActivity");
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }
}
