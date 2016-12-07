package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /** Called when the user clicks the login button */
    public void gotoChooseLang(View view) {
        // Do something in response to button
        Log.d("Welcome", "Starting Choose Language Activity");
        // do google authentication

        Intent intent = new Intent(this, ChooseLangActivity.class);
        startActivity(intent);
    }
}
