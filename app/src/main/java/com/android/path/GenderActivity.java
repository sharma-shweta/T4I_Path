package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.path.models.Gender;
import com.android.path.utils.FirebaseAPI;
import com.android.path.utils.SharedPreferencesAPI;


public class GenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
    }

    public void gotoDOB(View view) {
        Log.d("GenderActivity", "Starting DOBActivity");

        RadioGroup rg = (RadioGroup) findViewById(R.id.maleFemaleRadioGroup);
        int index = rg.indexOfChild(findViewById(rg.getCheckedRadioButtonId()));

        RadioButton rb = (RadioButton) rg.getChildAt(index);
        if (rb != null) {
            String selectedGenderStr = rb.getText().toString();
            Gender selectedGender = Gender.UNKNOWN;
            selectedGender = (selectedGenderStr.equals("Female")) ? Gender.FEMALE :Gender.MALE;
            FirebaseAPI.updateTeacher(SharedPreferencesAPI.getLoggedInUserID(this), FirebaseAPI.userGender, selectedGender);
        }

        Intent intent = new Intent(this, DOBActivity.class);
        startActivity(intent);
    }
}
