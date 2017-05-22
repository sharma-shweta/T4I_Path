package com.android.path;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.path.models.Gender;
import com.android.path.models.Student;
import com.android.path.models.StudentAnalytics;

import java.util.ArrayList;

public class PathHomeStudentsAdapter extends ArrayAdapter<StudentAnalytics> {

    private ArrayList<StudentAnalytics> objects;

    public PathHomeStudentsAdapter(Context context, int textViewResourceId, ArrayList<StudentAnalytics> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.home_student_item, null);
        }
        StudentAnalytics i = objects.get(position);
        if (i != null) {
            TextView studentName = (TextView) v.findViewById(R.id.home_student_name);
            TextView studentGPAChange = (TextView) v.findViewById(R.id.gpaChange);
            TextView studentGPAMovt = (TextView) v.findViewById(R.id.gpaMovement);

            studentName.setText(i.name);
            studentGPAMovt.setText(new Double(i.oldGPA).toString() + " \u2192 " + new Double(i.newGPA).toString());
            studentGPAChange.setText(String.format("%.2f", new Double(i.oldGPA - i.newGPA)));
        }
        return v;

    }
}