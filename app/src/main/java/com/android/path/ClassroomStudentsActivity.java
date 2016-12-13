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
        super.onCreate(savedInstanceState);
        boolean classHasStudents = hasStudents();
        setContentView(R.layout.activity_classroom_students);
        if(!classHasStudents){
            Button view_students = (Button)findViewById(R.id.btnViewClassroomStudents);
            view_students.setVisibility(View.VISIBLE);
            ImageButton add_students = (ImageButton)findViewById(R.id.btnAddStudents);
            add_students.setVisibility(View.INVISIBLE);
            TextView classroom_msg = (TextView) findViewById(R.id.addStudentMsg);
            String message = "We have found "+ getNoOfStudents() + " students in your class.";
            classroom_msg.setText(message);
        }
    }

    public void gotoAddStudent(View view) {
        Log.d("ClassroomStudentsAct", "Starting AddStudentActivity");
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }
    private boolean hasStudents(){
        return false;
    }
    private int getNoOfStudents(){
        return 5;
    }
}
