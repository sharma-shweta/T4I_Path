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

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void gotoSelSch(View view) {
        Log.d("ContactActivity", "Starting SelectSchoolActivity");

        EditText contact = (EditText)findViewById(R.id.teacher_contact_number);
        if (contact.getText() != null) {
            String phNum = contact.getText().toString();
            SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
            String userId = sharedPref.getString(getString(R.string.userIdSharedPref), "");
            FirebaseAPI.getInstance().updateTeacher(userId, "phNum", phNum);
        }

        Intent intent = new Intent(this, SelectSchoolActivity.class);
        startActivity(intent);
    }
}
