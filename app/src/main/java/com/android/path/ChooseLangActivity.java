package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ChooseLangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lang);
    }

    /** Called when the user clicks the login button */
    public void gotoGender(View view) {
        // Do something in response to button
        Log.d("ChooseLangActivity", "Starting GenderActivity");
        // do google authentication

        Intent intent = new Intent(this, GenderActivity.class);
        startActivity(intent);
    }
}
