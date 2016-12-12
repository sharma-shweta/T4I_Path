package com.android.path;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.path.models.Student;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    private ArrayList<Student> students = new ArrayList<Student>();
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // get listview
        listview = (ListView) findViewById(R.id.studentList);
        Student s = new Student();
        s.setName("Ram");
        s.setRollNum(123);
        students.add(s);

        ArrayAdapter<Student> adapter = new StudentArrayAdapter(this, R.layout.student_item, students);
        listview.setAdapter(adapter);
    }
}
