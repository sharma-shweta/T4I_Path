package com.android.path.utils;

import android.util.Log;

import com.android.path.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseAPI {
    private final String TAG = "FirebaseAPI";
    private static FirebaseAPI fbAPI;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("path-app");
    //private String loggedInUserId = SharedPreference.getInstance().get(getString(R.string.userIdSharedPref));
    private FirebaseAPI() {};

    public static FirebaseAPI getInstance() {
        if (fbAPI == null)
            fbAPI = new FirebaseAPI();
        return fbAPI;
    }

    public DatabaseReference getPathDBRef() {
        return mDatabase;
    }

    public void updateTeacher(String tchId, String teacherAttr, Object newValue){
        if (teacherAttr.equals("") || teacherAttr == null) {
            Log.v(TAG, "updateTeacher - teacherAttr not correctly set!");
            return;
        }
        if (newValue.equals("") || newValue == null) {
            Log.v(TAG, "updateTeacher - newValue not correctly set!");
            return;
        }

        DatabaseReference teacherRef = mDatabase.child("teachers");
        Map<String, Object> teacherUpdates = new HashMap<String, Object>();
        teacherUpdates.put(teacherAttr, newValue);
        teacherRef.child(tchId).updateChildren(teacherUpdates);
    }

}
