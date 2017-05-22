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

import com.android.path.utils.FirebaseAPI;
import com.android.path.utils.SharedPreferencesAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectSchoolActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<String> schools = new ArrayList<String>();
    private ArrayAdapter<String> schoolAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_school);

        String cnty = SharedPreferencesAPI.get(this, getString(R.string.userCountry), "");
        String state = SharedPreferencesAPI.get(this, getString(R.string.userState), "");
        String city = SharedPreferencesAPI.get(this, getString(R.string.userCity), "");

        final ValueEventListener schoolList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot scDS : dataSnapshot.getChildren()) {
                    schools.add(scDS.getKey());
                }
                schoolAdapter.addAll(schools);
                schoolAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("SelectSchoolActivity", "locationList:onCancelled", databaseError.toException());
            }
        };
        FirebaseAPI.getSchoolsInLocationDBRef(cnty, state, city).addListenerForSingleValueEvent(schoolList);

        AutoCompleteTextView schoolName = (AutoCompleteTextView) findViewById(R.id.schoolName);
        schoolAdapter = new ArrayAdapter<String>(SelectSchoolActivity.this, android.R.layout.select_dialog_item, new ArrayList<String>());
        schoolName.setAdapter(schoolAdapter);
        schoolName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                String selectionSchool = (String) parent.getItemAtPosition(position);
                SharedPreferencesAPI.put(SelectSchoolActivity.this, getString(R.string.userSchool), selectionSchool);
            }
        });
    }

    public void gotoSelClass(View view) {
        Log.d("SelectSchoolActivity", "Starting SelectClassroomActivity");
        Intent intent = new Intent(this, SelectClassroomActivity.class);
        startActivity(intent);
    }
}
