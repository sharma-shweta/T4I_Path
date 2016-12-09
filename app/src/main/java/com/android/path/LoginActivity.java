package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.path.models.SetUpFirebaseData;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /** Called when the user clicks the login button */
    public void loginToGoogle(View view) {
        // Do something in response to button
        Log.d("Login", "Logging in");
        // do google authentication

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.userIdSharedPref), "123");
        editor.commit();

        new SetUpFirebaseData().data();

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
