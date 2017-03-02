package com.android.path.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class SharedPreferencesAPI {
    private final String TAG = "SharedPreference";

    //Keys for SP values
    private static String sharedPrefKey = "SHAREDPREF";
    private static String userIDKey = "userId";


    public static void setLoggedInUserID(AppCompatActivity activity, String userId){
        SharedPreferences sharedPref = activity.getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(userIDKey, userId);
        editor.commit();
    }

    public static String getLoggedInUserID(AppCompatActivity activity){
        SharedPreferences sharedPref = activity.getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE);
        String userId = sharedPref.getString(userIDKey, null);
        return userId;
    }

    public static String get(AppCompatActivity activity, String attr, String defaultValue){
        SharedPreferences sharedPref = activity.getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE);
        String obj = sharedPref.getString(attr, defaultValue);
        return obj;
    }

    public static void put(AppCompatActivity activity, String key, String attr){
        SharedPreferences sharedPref = activity.getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, attr);
        editor.commit();
    }
}
