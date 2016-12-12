package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
        String user = sharedPref.getString(getString(R.string.userName), "");
        TextView hello = (TextView) findViewById(R.id.helloUser);
        if (user != null){
            hello.setText("Hello " + user + " !");
        }
        else{
            hello.setText("Hello!");
        }
    }

    public void gotoChooseLang(View view) {
        Log.d("Welcome", "Starting Choose Language Activity");
//        new SetUpFirebaseData().data();
        Intent intent = new Intent(this, AppLanguageActivity.class);
        startActivity(intent);
    }
}
