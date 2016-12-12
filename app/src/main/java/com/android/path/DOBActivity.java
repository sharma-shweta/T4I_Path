package com.android.path;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.android.path.models.Gender;
import com.android.path.utils.FirebaseAPI;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DOBActivity extends AppCompatActivity {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("path-app").child("teachers");
    private static String selDate;
    private static Button dobBtn;

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
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            SimpleDateFormat dobFmt = new SimpleDateFormat("dd-MM-yyyy");
            selDate = dobFmt.format(calendar.getTime());
            dobBtn.setText(selDate);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dob);
        dobBtn = (Button) findViewById(R.id.dob_btn);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        FragmentManager manager = getFragmentManager();
        newFragment.show(manager, "dobDatePicker");
    }

    public void gotoContact(View view) {
        Log.d("DOBActivity", "Starting ContactActivity");

        if (selDate != null) {
            SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
            String userId = sharedPref.getString(getString(R.string.userIdSharedPref), "");
            FirebaseAPI.getInstance().updateTeacher(userId, "dob", selDate);
        }
        else {
        }
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
}