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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationActivity extends AppCompatActivity {

    private static final String TAG = "LocationActivity";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("path-app").child("school-locations");
    private ArrayList<String> countries = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> states = new HashMap<String, ArrayList<String>>();
    private HashMap<String, ArrayList<String>> cities = new HashMap<String, ArrayList<String>>();

    private String selectionCnty = "";
    private String selectionState = "";
    private String selectionCity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ValueEventListener locationList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "locationList:onDataChange");
                for (DataSnapshot cntyDS : dataSnapshot.getChildren()) {
                    countries.add(cntyDS.getKey());
                    ArrayList<String> stateList = new ArrayList<String>();
                    for (DataSnapshot stDS : cntyDS.getChildren()) {
                        stateList.add(stDS.getKey());
                        ArrayList<String> cityList = new ArrayList<String>();
                        for (DataSnapshot ciDS : stDS.getChildren()) {
                            cityList.add(ciDS.getKey());
                        }
                        cities.put(stDS.getKey(), cityList);
                    }
                    states.put(cntyDS.getKey(), stateList);
                }

                AutoCompleteTextView countryName = (AutoCompleteTextView) findViewById(R.id.schoolCountry);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(LocationActivity.this, android.R.layout.select_dialog_item, countries);
                countryName.setThreshold(1);
                countryName.setAdapter(adapter1);

                countryName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                           selectionCnty = (String) parent.getItemAtPosition(position);
                           AutoCompleteTextView stateName = (AutoCompleteTextView)
                                   findViewById(R.id.schoolState);
                           ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                                   (LocationActivity.this, android.R.layout.select_dialog_item, states.get(selectionCnty));
                           stateName.setThreshold(1);
                           stateName.setAdapter(adapter2);
                           stateName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                  public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                                      selectionState = (String) parent.getItemAtPosition(position);
                                      AutoCompleteTextView cityName = (AutoCompleteTextView)
                                              findViewById(R.id.schoolCity);
                                      ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                                              (LocationActivity.this, android.R.layout.select_dialog_item, cities.get(selectionState));
                                      cityName.setThreshold(1);
                                      cityName.setAdapter(adapter3);
                                      cityName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                               public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                                                   selectionCity = (String) parent.getItemAtPosition(position);
                                               }
                                           }
                                      );
                                  }
                              }
                           );
                       }
                   }
                );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "locationList:onCancelled", databaseError.toException());
            }
        };

        mDatabase.addListenerForSingleValueEvent(locationList);

    }

    public void gotoGender(View view) {
        Log.d("AppLanguageActivity", "Starting GenderActivity");
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.userCountry), selectionCnty);
        editor.putString(getString(R.string.userState), selectionState);
        editor.putString(getString(R.string.userCity), selectionCity);
        editor.commit();
        Intent intent = new Intent(this, GenderActivity.class);
        startActivity(intent);
    }
}
