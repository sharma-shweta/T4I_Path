package com.android.path;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.android.path.utils.SharedPreferencesAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    ListView listview;
    ArrayAdapter<Student> adapter;
    ArrayList<Student> students;
    ArrayList<Student> allstudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        FirebaseAPI.getSchoolsDBRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d("StudentsChanged", "student change triggered");
                students = new ArrayList<>();
                allstudents = new ArrayList<>();
                String classId = SharedPreferencesAPI.get(AddStudentActivity.this, getString(R.string.classSharedPref), "");

                for (DataSnapshot std : snapshot.getChildren()) {
                    Student student = std.getValue(Student.class);
                    allstudents.add(student);
                    if (classId != null && student != null && student.currentClass != null && student.currentClass.equals(classId))
                        students.add(student);
                }

                listview = (ListView) findViewById(R.id.studentList);
                adapter = new StudentArrayAdapter(AddStudentActivity.this, R.layout.student_item, students);
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                        Student st = students.get(position);
                        st.currentClass = null;
                        AlertDialog.Builder adb = new AlertDialog.Builder(AddStudentActivity.this);
                        adb.setTitle("Delete?");
                        adb.setMessage("Are you sure you want to delete - Roll No: " + students.get(position).getRollNum() + " (" + students.get(position).getName() + ")");
                        adb.setNegativeButton("Cancel", null);
                        final Student s = st;
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAPI.addStudent(s);
                                students.remove(position);
                                TextView numOfStuds = (TextView) findViewById(R.id.numberOfStudents);
                                numOfStuds.setText(new Integer(students.size()).toString());
                                AddStudentActivity.this.adapter.notifyDataSetChanged();
                            }
                        });
                        adb.show();
                    }
                });
                TextView numOfStuds = (TextView) findViewById(R.id.numberOfStudents);
                numOfStuds.setText(new Integer(students.size()).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("StudentsChanged", "Could not load Students");
                String message = "Could not load Students";
                Toast.makeText(AddStudentActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addStudent(View view) {
        EditText rollNum = (EditText) findViewById(R.id.studRollNo);
        EditText name = (EditText) findViewById(R.id.studName);
        Spinner gender = (Spinner) findViewById(R.id.studGender);

        Gender genderStr = Gender.MALE;
        boolean genFlag = false;
        if (gender.getSelectedItem().toString().equals("Female")) {
            genderStr = Gender.FEMALE;
            genFlag = true;
        } else if (gender.getSelectedItem().toString().equals("Male")) {
            genderStr = Gender.MALE;
            genFlag = true;
        }
        if (rollNum.getText() == null || name.getText() == null || genFlag == false) {
            String message = "Fill all fields!";
            Toast.makeText(AddStudentActivity.this, message, Toast.LENGTH_SHORT).show();
            return;
        }

        String classId = SharedPreferencesAPI.get(this, getString(R.string.classSharedPref), "");
        Student existingStudent = null;
        boolean studentInList = false;
        for (Student s : students) {
            if (s.getRollNum() == Integer.parseInt(rollNum.getText().toString())) {
                existingStudent = s;
                studentInList = true;
                break;
            }
        }
        if (!studentInList) {
            // if not an existing student
            for (Student s : allstudents) {
                if (s.getRollNum() == Integer.parseInt(rollNum.getText().toString())) {
                    existingStudent = s;
                    break;
                }
            }
        }
        ArrayList<String> classRoomIds;
        if (existingStudent != null) {
            classRoomIds = existingStudent.classroomIds;
        } else {
            classRoomIds = new ArrayList<>();
        }
        if (!classRoomIds.contains(classId))
            classRoomIds.add(classId);
        if (existingStudent != null) {
            existingStudent.name = name.getText().toString();
            existingStudent.gender = genderStr;
            existingStudent.currentClass = classId;
            existingStudent.classroomIds = classRoomIds;
        } else {
            existingStudent = new Student(name.getText().toString(), Integer.parseInt(rollNum.getText().toString()),
                    genderStr, "dob", -1, classId, classRoomIds);
        }
        FirebaseAPI.addStudent(existingStudent);

        //reset values
        rollNum.setText("");
        name.setText("");
        gender.setSelection(0);
        String message = "Student Added";
        Toast.makeText(AddStudentActivity.this, message, Toast.LENGTH_SHORT).show();
        if (!studentInList) {
            students.add(existingStudent);
        }
        TextView numOfStuds = (TextView) findViewById(R.id.numberOfStudents);
        numOfStuds.setText(new Integer(students.size()).toString());
    }

    public void gotoExplorePath(View view) {
        Intent intent = new Intent(this, ExplorePath.class);
        startActivity(intent);
    }
}
