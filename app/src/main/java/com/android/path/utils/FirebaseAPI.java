package com.android.path.utils;

import android.util.Log;

import com.android.path.R;
import com.android.path.models.Student;
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
        if (tchId.equals("") || tchId == null) {
            Log.v(TAG, "updateTeacher - tchId not correctly set!");
            return;
        }
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

    public void addStudent(Student s){
        if (s == null) {
            Log.v(TAG, "addStudent - Student not correctly set!");
            return;
        }
        DatabaseReference studentRef = mDatabase.child("students");
        studentRef.child(new Integer(s.rollNum).toString()).setValue(s);
    }

    public void updateSubTeacher(String classId, String index, String teacherName){
        if (classId == null) {
            Log.v(TAG, "updateSubTeacher - classId=" + classId + " ");
            return;
        }
        DatabaseReference subTeacher = mDatabase.child("classes").child(classId).child("subjTeachers");
        subTeacher.child(index).child("teacherId").setValue(teacherName);
    }
}
