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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationActivity extends AppCompatActivity {

    private static final String TAG = "LocationActivity";

    private String selectionCnty = "";
    private String selectionState = "";
    private String selectionCity = "";

    private ArrayAdapter<String> cntyAdapter;
    private ArrayAdapter<String> stateAdapter;
    private ArrayAdapter<String> cityAdapter;

    private ArrayList<String> countries = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> states = new HashMap<String, ArrayList<String>>();
    private HashMap<String, ArrayList<String>> cities = new HashMap<String, ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ValueEventListener locationList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "LocationActivity: onDataChange called");
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
                cntyAdapter.addAll(countries);
                cntyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "locationList:onCancelled", databaseError.toException());
            }
        };

        FirebaseAPI.getSchoolLocationsDBRef().addListenerForSingleValueEvent(locationList);

        AutoCompleteTextView countryName = (AutoCompleteTextView) findViewById(R.id.schoolCountry);
        cntyAdapter = new ArrayAdapter<String>(LocationActivity.this, android.R.layout.select_dialog_item, new ArrayList<String>());
        countryName.setAdapter(cntyAdapter);
        countryName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                selectionCnty = (String) parent.getItemAtPosition(pos);
                stateAdapter.addAll(states.get(selectionCnty));
                stateAdapter.notifyDataSetChanged();
            }
        });

        AutoCompleteTextView stateName = (AutoCompleteTextView) findViewById(R.id.schoolState);
        stateAdapter = new ArrayAdapter<String>(LocationActivity.this, android.R.layout.select_dialog_item, new ArrayList<String>());
        stateName.setAdapter(stateAdapter);
        stateName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                selectionState = (String) parent.getItemAtPosition(pos);
                cityAdapter.addAll(cities.get(selectionState));
                cityAdapter.notifyDataSetChanged();
            }
        });

        AutoCompleteTextView cityName = (AutoCompleteTextView) findViewById(R.id.schoolCity);
        cityAdapter = new ArrayAdapter<String>(LocationActivity.this, android.R.layout.select_dialog_item, new ArrayList<String>());
        cityName.setAdapter(cityAdapter);
        cityName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                selectionCity = (String) parent.getItemAtPosition(pos);
            }
        });
    }

    public void gotoGender(View view) {
        Log.d("AppLanguageActivity", "Starting GenderActivity");
        SharedPreferencesAPI.put(this, getString(R.string.userCountry), selectionCnty);
        SharedPreferencesAPI.put(this, getString(R.string.userState), selectionState);
        SharedPreferencesAPI.put(this, getString(R.string.userCity), selectionCity);
        Intent intent = new Intent(this, GenderActivity.class);
        startActivity(intent);
    }
}
