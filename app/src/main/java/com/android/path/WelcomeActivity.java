package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.path.models.SetUpFirebaseData;
import com.android.path.utils.SharedPreferencesAPI;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        String username = SharedPreferencesAPI.get(this, getString(R.string.userName), "");
        String helloUser = String.format("Hello %s!", username.split(" ")[0]);
        TextView hello = (TextView) findViewById(R.id.helloUser);
        hello.setText(helloUser);
    }

    public void gotoChooseLang(View view) {
        Log.d("Welcome", "Starting Choose Language Activity");
        Intent intent = new Intent(this, AppLanguageActivity.class);
        startActivity(intent);
    }
}
