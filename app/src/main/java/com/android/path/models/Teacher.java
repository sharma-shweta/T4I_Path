package com.android.path.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher {
    public String name;
    public String phNum;
    public String email;
    public String loginId;
    public ArrayList<String> subjectIds;

    public Teacher() {
        // Default constructor required for calls to DataSnapshot.getValue(Teacher.class)
    }

    public Teacher(String name, String phNum, String email, String loginId, ArrayList<String> subjectIds) {
        this.name = name;
        this.phNum = phNum;
        this.email = email;
        this.loginId = loginId;
        this.subjectIds = subjectIds;
    }
}
