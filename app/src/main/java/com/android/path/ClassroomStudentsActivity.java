package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ClassroomStudentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean classHasStudents = hasStudents();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_students);
        if(classHasStudents){
            Button view_students = (Button)findViewById(R.id.btnViewClassroomStudents);
            view_students.setVisibility(View.VISIBLE);
            ImageButton add_students = (ImageButton)findViewById(R.id.btnAddStudents);
            add_students.setVisibility(View.INVISIBLE);
            TextView classroom_msg = (TextView) findViewById(R.id.addStudentMsg);
            String message = "We have found "+ getNoOfStudents() + "students in your class";
            classroom_msg.setText(message);
        }
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_classroom_students);

    }
    public void gotoAddStudent(View view) {
        // Do something in response to button
        Log.d("AddStudentActivity", "Starting AddStudentActivity");
        // do google authentication

        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }
    private boolean hasStudents(){
        return false;
    }
    private int getNoOfStudents(){
        return 23;
    }
}
