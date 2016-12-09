package com.android.path;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AppLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_language);
    }

    /** Called when the user clicks the login button */
    public void gotoLocation(View view) {
        // Do something in response to button
        Log.d("AppLanguageActivity", "Starting LocationActivity");
        // do google authentication

        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }
}
