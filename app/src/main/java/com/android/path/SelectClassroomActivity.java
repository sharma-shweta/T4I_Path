package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.android.path.models.Classroom;
import com.android.path.utils.FirebaseAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectClassroomActivity extends AppCompatActivity {

    private ArrayList<String> classes = new ArrayList<String>();

    private String selClassGrade = "";
    private String selClassSection = "";
    private HashMap<String, HashMap<String, String>> classGradeSection = new HashMap<String, HashMap<String, String>>();

    private DatabaseReference mDatabase = FirebaseAPI.getInstance().getPathDBRef().child("schools");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_classroom);

        ValueEventListener classList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot clsId : dataSnapshot.getChildren()) {
                    classGradeSection.put(clsId.getValue().toString(), null);
                }

                DatabaseReference classDB = FirebaseAPI.getInstance().getPathDBRef().child("classes");
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot cls : dataSnapshot.getChildren()) {
                            if (classGradeSection.containsKey(cls.getKey())) {
                                Classroom klass = dataSnapshot.getValue(Classroom.class);
                                HashMap<String, String> cgs = new HashMap<String, String>();
                                cgs.put("Section", klass.section);
                                cgs.put("Grade", klass.grade);
                                classGradeSection.put(cls.getKey(), cgs);
                            }
                            HashMap<String, String> cgs = new HashMap<String, String>();
                            classGradeSection.put(cls.getKey(), cgs);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                System.out.println(classGradeSection);




//                AutoCompleteTextView schoolName = (AutoCompleteTextView) findViewById(R.id.schoolName);
//                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SelectSchoolActivity.this, android.R.layout.select_dialog_item, schools);
//                schoolName.setThreshold(1);
//                schoolName.setAdapter(adapter1);
//                schoolName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
//                        selectionSchool = (String) parent.getItemAtPosition(position);
//                        SharedPreferences sharedPref = SelectSchoolActivity.this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPref.edit();
//                        editor.putString(getString(R.string.userSchool), selectionSchool);
//                        editor.commit();
//                    }
//                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("SelectSchoolActivity", "locationList:onCancelled", databaseError.toException());
            }
        };

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
        String userSchool = sharedPref.getString(getString(R.string.userSchool), "");

        mDatabase.child("ACJC").child("classIds").addListenerForSingleValueEvent(classList);
    }

    public void gotoSelSubj(View view) {
        Log.d("SelectClassroomActivity", "Starting SelectSubjectActivity");
        Intent intent = new Intent(this, SelectSubjectActivity.class);
        startActivity(intent);
    }
}

