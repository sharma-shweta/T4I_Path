package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.path.models.Classroom;
import com.android.path.utils.FirebaseAPI;
import com.android.path.utils.SharedPreferencesAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectClassroomActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String selClassGrade = "";
    private String selClassSection = "";
    private HashMap<String, ArrayList<Pair<String, String>>> gradeClassSection = new HashMap<String, ArrayList<Pair<String, String>>>();
    private ArrayList<String> mClasses = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_classroom);

        ValueEventListener classList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot clsId : dataSnapshot.getChildren()) {
                    mClasses.add(clsId.getValue().toString());
                }

                DatabaseReference classDB = FirebaseAPI.getClassesDBRef();
                classDB.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot cls : dataSnapshot.getChildren()) {
                            String className = cls.getKey();
                            if (mClasses.contains(className)) {
                                Classroom klass = cls.getValue(Classroom.class);
                                String grade = klass.grade;
                                if (gradeClassSection.containsKey(grade)) {
                                    ArrayList<Pair<String, String>> classSection = gradeClassSection.get(grade);
                                    classSection.add(new Pair<>(className, klass.section));
                                    gradeClassSection.put(grade, classSection);
                                } else {
                                    ArrayList<Pair<String, String>> classSection = new ArrayList<Pair<String, String>>();
                                    classSection.add(new Pair<>(className, klass.section));
                                    gradeClassSection.put(grade, classSection);
                                }
                            }
                        }
                        Spinner spinner = (Spinner) findViewById(R.id.spGrade);
                        spinner.setOnItemSelectedListener(SelectClassroomActivity.this);
                        List<String> grades = new ArrayList<>();
                        for (String g : gradeClassSection.keySet())
                            grades.add(g);
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(SelectClassroomActivity.this, android.R.layout.simple_spinner_item, grades);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(dataAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("data connection error =" + databaseError);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("SelectSchoolActivity", "locationList:onCancelled", databaseError.toException());
            }
        };

        String userSchool = SharedPreferencesAPI.get(this, getString(R.string.userSchool), "");
        FirebaseAPI.getSchoolClassIdsDBRef(userSchool).addListenerForSingleValueEvent(classList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        Log.d("SelectClassroomActivity", "Starting SelectSubjectActivity" + item);
        Spinner spinner = (Spinner) findViewById(R.id.spSection);
        spinner.setOnItemSelectedListener(SelectClassroomActivity.this);
        ArrayList<Pair<String, String>> classSection = gradeClassSection.get(item);
        Log.d("SelectClassroomActivity", "classSection size " + classSection);
        if (classSection == null)
            return;
        List<String> sections = new ArrayList<>();
        for (Pair<String, String> p : classSection)
            sections.add(p.second);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SelectClassroomActivity.this, android.R.layout.simple_spinner_item, sections);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }

    public void gotoSelSubj(View view) {
        Spinner grade = (Spinner) findViewById(R.id.spGrade);
        Spinner section = (Spinner) findViewById(R.id.spSection);
        String g = (String) grade.getSelectedItem();
        String s = (String) section.getSelectedItem();
        if (g == null || s == null) {
            Toast.makeText(SelectClassroomActivity.this, "Please select values.", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferencesAPI.put(SelectClassroomActivity.this, getString(R.string.gradeSharefPref), g);
        SharedPreferencesAPI.put(SelectClassroomActivity.this, getString(R.string.sectionSharedPref), s);

        ArrayList<Pair<String, String>> classSection = gradeClassSection.get(g);
        Log.d("SelectClassroomActivity", "grade=" + g + " section=" + s + " classSection=" + classSection);

        for (Pair<String, String> p : classSection)
            if (s == p.second) {
                Log.d("SelectClassroomActivity","putting class=" + p.first);
                SharedPreferencesAPI.put(SelectClassroomActivity.this, getString(R.string.classSharedPref), p.first);
                break;
            }

        Intent intent = new Intent(this, SelectSubjectActivity.class);
        startActivity(intent);
    }
}
