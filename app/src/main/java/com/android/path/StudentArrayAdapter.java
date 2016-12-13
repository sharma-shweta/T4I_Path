package com.android.path;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.path.models.Gender;
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
            v = inflater.inflate(R.layout.add_student_row, null);
        }
        Student i = objects.get(position);
        if (i != null) {
            TextView studentRollNo = (TextView) v.findViewById(R.id.rowStudRollNum);
            TextView studentGender = (TextView) v.findViewById(R.id.rowStudGender);
            TextView studentName = (TextView) v.findViewById(R.id.rowStudName);

            if (studentRollNo != null) {
                studentRollNo.setText(new Integer(i.rollNum).toString());
            }
            if (studentGender != null) {
                if (i.gender.equals(Gender.FEMALE))
                    studentGender.setText("F");
                if (i.gender.equals(Gender.MALE))
                    studentGender.setText("M");
            }
            if (studentName != null) {
                studentName.setText(i.name);
            }
        }
        return v;

    }
}
