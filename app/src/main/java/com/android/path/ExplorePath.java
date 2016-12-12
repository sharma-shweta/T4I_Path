package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.path.utils.FirebaseAPI;

public class ExplorePath extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_path);
    }

    public void gotoClassData(View view) {
        Log.d("ExplorePath", "Starting ClassroomData");
        Intent intent = new Intent(this, ClassroomData.class);
        startActivity(intent);
    }
}
