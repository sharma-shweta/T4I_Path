package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
    }

    /** Called when the user clicks the login button */
    public void gotoClsStud(View view) {
        // Do something in response to button
        Log.d("SelectSubjectActivity", "Starting SelectSubjectActivity");
        // do google authentication

//        mDatabase.child("shwt-test").child("android-test").setValue("shweta");

        Intent intent = new Intent(this, ClassroomStudentsActivity.class);
        startActivity(intent);
    }
}