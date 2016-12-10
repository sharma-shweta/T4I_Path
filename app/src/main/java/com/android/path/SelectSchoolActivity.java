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

    private String selectionCnty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_school);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
        String cnty = sharedPref.getString(getString(R.string.userCountry), "");
        String state = sharedPref.getString(getString(R.string.userState), "");
        String city = sharedPref.getString(getString(R.string.userCity), "");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("path-app").child("school-locations").child(cnty).child(state).child(city);

        ValueEventListener schoolList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot scDS : dataSnapshot.getChildren()) {
                    schools.add(scDS.getKey());
                }

                AutoCompleteTextView schoolName = (AutoCompleteTextView) findViewById(R.id.schoolName);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SelectSchoolActivity.this, android.R.layout.select_dialog_item, schools);
                schoolName.setThreshold(1);
                schoolName.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("SelectSchoolActivity", "locationList:onCancelled", databaseError.toException());
            }
        };

        mDatabase.addListenerForSingleValueEvent(schoolList);

    }

    public void gotoSelClass(View view) {
        Log.d("SelectSchoolActivity", "Starting SelectClassroomActivity");
        Intent intent = new Intent(this, SelectClassroomActivity.class);
        startActivity(intent);
    }
}
