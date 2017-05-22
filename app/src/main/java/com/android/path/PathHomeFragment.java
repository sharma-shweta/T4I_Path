package com.android.path;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.path.models.StudentAnalytics;
import com.android.path.models.SubjectAnalytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathHomeFragment extends Fragment implements View.OnClickListener {

    static final ArrayList<StudentAnalytics> students = new ArrayList<StudentAnalytics>();
    ListView listview;
    ArrayAdapter<StudentAnalytics> adapter;
    private RecyclerView subjCardsRecyclerView;
    private SubjectAnalyticsAdapter subjDataAdapter;
    private List<SubjectAnalytics> subjectAnalytics = new ArrayList<>();

    protected View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_path_home, container,
                false);

        this.mView = rootView;

        //mock students
        StudentAnalytics sa1 = new StudentAnalytics("Vaibhav Gupta", 1, 2.9, 2.8);
        StudentAnalytics sa2 = new StudentAnalytics("Shubham Kaushal", 2, 3.2, 2.5);
        students.add(sa1);
        students.add(sa2);
        students.add(sa1);
        students.add(sa2);
        students.add(sa1);
        students.add(sa2);
        students.add(sa1);
        students.add(sa2);

        listview = (ListView) mView.findViewById(R.id.movedDownList);
        adapter =  new PathHomeStudentsAdapter(getActivity(), R.layout.home_student_item, students);
        listview.setAdapter(adapter);

        listview = (ListView) mView.findViewById(R.id.movedUpList);
        adapter =  new PathHomeStudentsAdapter(getActivity(), R.layout.home_student_item, students);
        listview.setAdapter(adapter);

        subjectAnalytics = getSubjAnalytics("All");

        subjCardsRecyclerView = (RecyclerView) mView.findViewById(R.id.subjectCardsRV);
        subjDataAdapter = new SubjectAnalyticsAdapter(subjectAnalytics);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        subjCardsRecyclerView.setLayoutManager(mLayoutManager);
        subjCardsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        subjCardsRecyclerView.setAdapter(subjDataAdapter);

        Button allSubj = (Button) mView.findViewById(R.id.allSubs);
        Button mathSubj = (Button) mView.findViewById(R.id.mathSub);
        Button readingSubj = (Button) mView.findViewById(R.id.readingSub);
        Button writingSubj = (Button) mView.findViewById(R.id.writingSub);
        allSubj.setOnClickListener(this);
        mathSubj.setOnClickListener(this);
        readingSubj.setOnClickListener(this);
        writingSubj.setOnClickListener(this);

        return rootView;
    }



    public ArrayList<SubjectAnalytics> getSubjAnalytics(String subjName) {
        //mock subject analytics
        Map<Double, Double> gpaStudentRatioMap = new HashMap<Double, Double>();
        gpaStudentRatioMap.put(0.0, 12.0);  //gpa, % of students in this grade
        gpaStudentRatioMap.put(1.0, 32.0);
        gpaStudentRatioMap.put(2.0, 22.0);
        gpaStudentRatioMap.put(3.0, 26.0);
        gpaStudentRatioMap.put(4.0, 2.0);

        Map<Double, Double> gpaStudentRatioMap2 = new HashMap<Double, Double>(gpaStudentRatioMap);
        gpaStudentRatioMap2.remove(3.0);

        SubjectAnalytics sbAn1 = new SubjectAnalytics("Reading", 3.2, 24.0, gpaStudentRatioMap);
        SubjectAnalytics sbAn2 = new SubjectAnalytics("Math", 3.2, 24.0, gpaStudentRatioMap);
        SubjectAnalytics sbAn3 = new SubjectAnalytics("Writing", 3.2, 24.0, gpaStudentRatioMap2);

        ArrayList<SubjectAnalytics> l = new ArrayList<SubjectAnalytics>();
        if (subjName.equals("Math"))
            l.add(sbAn2);
        if (subjName.equals("Reading"))
            l.add(sbAn1);
        if (subjName.equals("Writing"))
            l.add(sbAn3);
        if (subjName.equals("All")) {
            l.add(sbAn1);
            l.add(sbAn2);
            l.add(sbAn3);
        }
        return l;
    }

    public void onClick(View v) {
        Button allSubj = (Button) mView.findViewById(R.id.allSubs);
        Button mathSubj = (Button) mView.findViewById(R.id.mathSub);
        Button readingSubj = (Button) mView.findViewById(R.id.readingSub);
        Button writingSubj = (Button) mView.findViewById(R.id.writingSub);

        allSubj.setBackgroundResource(R.drawable.rounded_button);
        mathSubj.setBackgroundResource(R.drawable.rounded_button);
        readingSubj.setBackgroundResource(R.drawable.rounded_button);
        writingSubj.setBackgroundResource(R.drawable.rounded_button);

        switch (v.getId()) {
            case R.id.allSubs:
                allSubj.setBackgroundResource(R.drawable.btn_pressed);
                subjectAnalytics.clear();
                subjectAnalytics.addAll(getSubjAnalytics("All"));
                subjDataAdapter.notifyDataSetChanged();
                break;
            case R.id.mathSub:
                mathSubj.setBackgroundResource(R.drawable.btn_pressed);
                subjectAnalytics.clear();
                subjectAnalytics.addAll(getSubjAnalytics("Math"));
                subjDataAdapter.notifyDataSetChanged();
                break;
            case R.id.readingSub:
                readingSubj.setBackgroundResource(R.drawable.btn_pressed);
                subjectAnalytics.clear();
                subjectAnalytics.addAll(getSubjAnalytics("Reading"));
                subjDataAdapter.notifyDataSetChanged();
                break;
            case R.id.writingSub:
                writingSubj.setBackgroundResource(R.drawable.btn_pressed);
                subjectAnalytics.clear();
                subjectAnalytics.addAll(getSubjAnalytics("Writing"));
                subjDataAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}
