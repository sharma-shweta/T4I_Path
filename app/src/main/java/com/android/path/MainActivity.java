package com.android.path;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;

import com.android.path.models.Classroom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
