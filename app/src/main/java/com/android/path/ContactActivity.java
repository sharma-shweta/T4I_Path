package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void gotoSelSch(View view) {
        Log.d("ContactActivity", "Starting SelectSchoolActivity");
        Intent intent = new Intent(this, SelectSchoolActivity.class);
        startActivity(intent);
    }
}
