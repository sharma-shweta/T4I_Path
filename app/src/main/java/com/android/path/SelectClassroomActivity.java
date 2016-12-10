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

    public void gotoSelSubj(View view) {
        Log.d("SelectClassroomActivity", "Starting SelectSubjectActivity");
        Intent intent = new Intent(this, SelectSubjectActivity.class);
        startActivity(intent);
    }
}

