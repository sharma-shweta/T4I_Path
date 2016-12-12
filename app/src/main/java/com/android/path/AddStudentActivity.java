package com.android.path;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.path.models.Gender;
import com.android.path.models.Student;
import com.android.path.utils.FirebaseAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class AddStudentActivity extends AppCompatActivity {

    private DatabaseReference mDatabase = FirebaseAPI.getInstance().getPathDBRef().child("students");

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<Student> students = new ArrayList<Student>();
                for (DataSnapshot std : snapshot.getChildren()) {
                    Student student = std.getValue(Student.class);
                    students.add(student);
                }
                listview = (ListView) findViewById(R.id.studentList);
                ArrayAdapter<Student> adapter = new StudentArrayAdapter(AddStudentActivity.this, R.layout.student_item, students);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String message = "Could not load Students";
                Toast.makeText(AddStudentActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addStudent(View view){
        EditText rollNum = (EditText)findViewById(R.id.studRollNo);
        EditText name = (EditText)findViewById(R.id.studName);
        Spinner gender = (Spinner) findViewById(R.id.studGender);
        Gender genderStr = Gender.MALE;
        boolean genFlag = false;

        if (gender.getSelectedItem().toString().equals("Female")) {
            genderStr = Gender.FEMALE;
            genFlag = true;
        }
        else if(gender.getSelectedItem().toString().equals("Male")){
            genderStr = Gender.MALE;
            genFlag = true;
        }

        if (rollNum.getText() == null || name.getText() == null || gender.getSelectedItem() == null) {
            String message = "DONT BE THE DUMB!!";
            Toast.makeText(AddStudentActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        Student student = new Student(name.getText().toString(), Integer.parseInt(rollNum.getText().toString()),
                genderStr, "dob", -1, new ArrayList<String>());
        FirebaseAPI.getInstance().addStudent(student);

        //reset values
        rollNum.setText("");
        name.setText("");

    }
}
