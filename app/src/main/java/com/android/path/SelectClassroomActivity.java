package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectClassroomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_classroom);
    }

    /** Called when the user clicks the login button */
    public void gotoSelSubj(View view) {
        // Do something in response to button
        Log.d("SelectClassroomActivity", "Starting SelectSubjectActivity");
        // do google authentication

//        mDatabase.child("shwt-test").child("android-test").setValue("shweta");

        Intent intent = new Intent(this, SelectSubjectActivity.class);
        startActivity(intent);
    }
}

