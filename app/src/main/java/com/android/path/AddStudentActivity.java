package com.android.path;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    private DatabaseReference mDatabase = FirebaseAPI.getInstance().getPathDBRef().child("students");

    ListView listview;
    ArrayAdapter<Student> adapter;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                students = new ArrayList<Student>();
                for (DataSnapshot std : snapshot.getChildren()) {
                    Student student = std.getValue(Student.class);
                    students.add(student);
                }
                listview = (ListView) findViewById(R.id.studentList);
                adapter = new StudentArrayAdapter(AddStudentActivity.this, R.layout.student_item, students);
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        AlertDialog.Builder adb=new AlertDialog.Builder(AddStudentActivity.this);
                        adb.setTitle("Delete?");
                        adb.setMessage("Are you sure you want to delete - Roll No: " + students.get(position).getRollNum() + " (" + students.get(position).getName() + ")");
                        final int positionToRemove = position;
                        adb.setNegativeButton("Cancel", null);
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                students.remove(positionToRemove);
                                AddStudentActivity.this.adapter.notifyDataSetChanged();
                            }});
                        adb.show();
                    }
                });
                TextView numOfStuds = (TextView) findViewById(R.id.numberOfStudents);
                numOfStuds.setText(new Integer(students.size()).toString());

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

        if (rollNum.getText() == null || name.getText() == null || genFlag == false) {
            String message = "Fill all fields!";
            Toast.makeText(AddStudentActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
        String classId = sharedPref.getString(getString(R.string.classSharedPref), "");
        Student student = new Student(name.getText().toString(), Integer.parseInt(rollNum.getText().toString()),
                genderStr, "dob", -1, classId, new ArrayList<String>());
        FirebaseAPI.getInstance().addStudent(student);

        //reset values
        rollNum.setText("");
        name.setText("");
        gender.setSelection(0);

        String message = "Student Added";
        Toast.makeText(AddStudentActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void gotoExplorePath(View view) {
        Intent intent = new Intent(this, ExplorePath.class);
        startActivity(intent);
    }
}
