package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.path.utils.FirebaseAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SelectSubjectActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
    }

    public void gotoClsStud(View view) {
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
        String userId = sharedPref.getString(getString(R.string.userIdSharedPref), "");
        String classId = sharedPref.getString(getString(R.string.classSharedPref), "");
        Log.d("SelectSubjectActivity", "User Shared Pref: userId " + userId + " classId " + classId);
        if (userId == null || classId == null) {
            Toast.makeText(SelectSubjectActivity.this, "not authorized login/class not selected properly. Contact support.", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("SelectSubjectActivity", "writing to the firebase for teachers");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("path-app").child("classes").child(classId).child("subjTeachers");
        ValueEventListener subTeacherList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("On classes change");
                CheckBox mathcb = (CheckBox)findViewById(R.id.math_checkbox);
                CheckBox englishcb = (CheckBox)findViewById(R.id.english_checkbox);
                SharedPreferences sharedPref = SelectSubjectActivity.this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
                String classId = sharedPref.getString(getString(R.string.classSharedPref), "");
                String userId = sharedPref.getString(getString(R.string.userIdSharedPref), "");
                for(DataSnapshot d: dataSnapshot.getChildren()) {
                    String k = d.getKey();
                    HashMap<String, String> m = (HashMap<String, String>)d.getValue();
                    for(String key: m.keySet()) {
                        if(m.get(key).equals("english") && englishcb.isChecked()) {
                            FirebaseAPI.getInstance().updateSubTeacher(classId, k, userId);
                        } else if(m.get(key).equals("mathematics") && mathcb.isChecked()) {
                            FirebaseAPI.getInstance().updateSubTeacher(classId, k, userId);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ClassRoomStudentsAct", "locationList:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addListenerForSingleValueEvent(subTeacherList);

        Intent intent = new Intent(this, ClassroomStudentsActivity.class);
        startActivity(intent);
    }
}