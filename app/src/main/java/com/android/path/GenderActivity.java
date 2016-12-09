package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GenderActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_gender);
    }

    /** Called when the user clicks the login button */
    public void gotoDOB(View view) {
        // Do something in response to button
        Log.d("GenderActivity", "Starting DOBActivity");
        // do google authentication

        Intent intent = new Intent(this, DOBActivity.class);
        startActivity(intent);
    }
}
