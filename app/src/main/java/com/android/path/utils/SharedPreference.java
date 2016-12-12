package com.android.path.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.android.path.R;

public class SharedPreference extends AppCompatActivity {
    // DO NOT USE THIS YET
    private final String TAG = "SharedPreference";
    private static SharedPreference shPref;

    private SharedPreference() {};

    public static SharedPreference getInstance() {
        if (shPref == null)
            shPref = new SharedPreference();
        return shPref;
    }

//    public void getLoggedInUserID(){
//        return this.getSharedPreferences(getString(getString(R.string.userIdSharedPref), ""));
//    }

    public Object get(String attr){
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.SHAREDPREF), Context.MODE_PRIVATE);
        Object obj = sharedPref.getString(attr, "NOT FOUND");
        return obj;
    }


}
