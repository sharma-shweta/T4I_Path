package com.android.path.utils;

import android.util.Log;

import com.android.path.R;
import com.android.path.models.Gender;
import com.android.path.models.Student;
import com.android.path.models.Teacher;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseAPI {
    private final static String TAG = "FirebaseAPI";
    private final static String root = "path-app";

    public static String users = "teachers";
    public static String schoolLocations = "school-locations";
    public static String userGender = "gender";
    public static String userDOB = "dob";
    public static String userPhNum = "phNum";
    public static String schools = "schools";
    public static String classIds = "classIds";
    public static String classes = "classes";
    public static String students = "students";
    public static String subjTeachers = "subjTeachers";


    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(root);

    public static DatabaseReference getPathDBRef() {
        return mDatabase;
    }

    public static DatabaseReference getUsersDBRef() {
        return mDatabase.child(users);
    }

    public static DatabaseReference getSchoolLocationsDBRef() {
        return mDatabase.child(schoolLocations);
    }

    public static DatabaseReference getSchoolsInLocationDBRef(String cnty, String state, String city) {
        return mDatabase.child(schoolLocations).child(cnty).child(state).child(city);
    }

    public static DatabaseReference getSchoolsDBRef() {
        return mDatabase.child(schools);
    }

    public static DatabaseReference getSchoolClassIdsDBRef(String userSchool) {
        return mDatabase.child(schools).child(userSchool).child(classIds);
    }

    public static DatabaseReference getClassesDBRef() {
        return mDatabase.child(classes);
    }

    public static DatabaseReference getClsSubjectTeachers(String classId) {
        return mDatabase.child(classes).child(classId).child(subjTeachers);
    }

    public static DatabaseReference getStudentsDBRef() {
        return mDatabase.child(students);
    }


    /**
     * Teacher CRUD operations
     */
    public static void addUser(Teacher t) {
        getUsersDBRef().child(t.loginId).setValue(t);
    }

    public static void updateTeacher(String tchId, String teacherAttr, Object newValue){
        if (tchId == null || tchId.equals("")) {
            Log.v(TAG, "updateTeacher - tchId not correctly set!");
            return;
        }
        if (teacherAttr == null || teacherAttr.equals("")) {
            Log.v(TAG, "updateTeacher - teacherAttr not correctly set!");
            return;
        }
        if (newValue == null || newValue.equals("")) {
            Log.v(TAG, "updateTeacher - newValue not correctly set!");
            return;
        }
        getUsersDBRef().child(tchId).child(teacherAttr).setValue(newValue);
    }

    public static void addStudent(Student s){
        if (s == null) {
            Log.v(TAG, "addStudent - Student not correctly set!");
            return;
        }
        DatabaseReference studentRef = mDatabase.child("students");
        studentRef.child(new Integer(s.rollNum).toString()).setValue(s);
    }

    public static void updateSubTeacher(String classId, String index, String teacherName){
        if (classId == null) {
            Log.v(TAG, "updateSubTeacher - classId=" + classId + " ");
            return;
        }
        DatabaseReference subTeacher = mDatabase.child("classes").child(classId).child("subjTeachers");
        subTeacher.child(index).child("teacherId").setValue(teacherName);
    }


}
