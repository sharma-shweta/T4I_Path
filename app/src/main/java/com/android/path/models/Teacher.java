package com.android.path.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Teacher {
    public String name;
    public String phNum;
    public String email;
    public String loginId;
    public ArrayList<String> subjectIds;
    public Gender gender;
    public String dob;

    public Teacher() {
        // Default constructor required for calls to DataSnapshot.getValue(Teacher.class)
    }

    public Teacher(String name, String phNum, String email, String loginId, Gender gender, String dob,
                   ArrayList<String> subjectIds) {
        this.name = name;
        this.phNum = phNum;
        this.email = email;
        this.loginId = loginId;
        this.gender = gender;
        this.dob = dob;
        this.subjectIds = subjectIds;
    }
}
