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


public class GenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        RadioGroup rg = (RadioGroup) findViewById(R.id.maleFemaleRadioGroup);
        rg.setSelected(true);
    }

    public void gotoDOB(View view) {
        Log.d("GenderActivity", "Starting DOBActivity");

        RadioGroup rg = (RadioGroup) findViewById(R.id.maleFemaleRadioGroup);
        int index = rg.indexOfChild(findViewById(rg.getCheckedRadioButtonId()));

        RadioButton r = (RadioButton) rg.getChildAt(index);
        String selectedGenderStr = r.getText().toString();
        Gender selectedGender = Gender.MALE;

        if (selectedGenderStr.equals("Female")) {
            selectedGender = Gender.FEMALE;
        }
        else{
            selectedGender = Gender.MALE;
        }

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
        String userId = sharedPref.getString(getString(R.string.userIdSharedPref), "");
        if (userId != null) {
            FirebaseAPI.getInstance().updateTeacher(userId, "gender", selectedGender);
        }

        Intent intent = new Intent(this, DOBActivity.class);
        startActivity(intent);
    }
}
