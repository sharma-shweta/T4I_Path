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

    public void gotoClsStud(View view) {
        Log.d("SelectSubjectActivity", "Starting SelectSubjectActivity");
        Intent intent = new Intent(this, ClassroomStudentsActivity.class);
        startActivity(intent);
    }
}