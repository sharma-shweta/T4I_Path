package com.android.path;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.path.models.Student;

import java.util.ArrayList;

class StudentArrayAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> objects;

    public StudentArrayAdapter(Context context, int textViewResourceId, ArrayList<Student> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.student_item, null);
        }
        Student i = objects.get(position);
        if (i != null) {
            TextView rollNoText = (TextView) v.findViewById(R.id.leftstudent);
            TextView rollNo = (TextView) v.findViewById(R.id.rightstudent);
            TextView studentNameText = (TextView) v.findViewById(R.id.leftrollno);
            TextView studentName = (TextView) v.findViewById(R.id.rightrollno);

            if (rollNoText != null) {
                rollNoText.setText("Roll No: ");
            }
            if (rollNo != null) {
                rollNo.setText(new Integer(i.getRollNum()).toString());
            }
            if (studentNameText != null) {
                studentNameText.setText("Student Name: ");
            }
            if (studentName != null) {
                studentName.setText(i.getName());
            }
        }
        return v;

    }
}
