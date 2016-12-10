package com.android.path;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

public class DOBActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dob);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        FragmentManager manager = getFragmentManager();
        newFragment.show(manager, "dobDatePicker");
    }

    /** Called when the user clicks the login button */
    public void gotoContact(View view) {
        // Do something in response to button
        Log.d("GenderActivity", "Starting ContactActivity");
        // do google authentication

//        mDatabase.child("shwt-test").child("android-test").setValue("shweta");

        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
}