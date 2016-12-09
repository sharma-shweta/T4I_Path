package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;

public class DOBActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dob);
    }

    /** Called when the user clicks the login button */
    public void gotoContact(View view) {
        // Do something in response to button
        Log.d("GenderActivity", "Starting ContactActivity");
        // do google authentication

//        mDatabase.child("shwt-test").child("android-test").setValue("shweta");

        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
}