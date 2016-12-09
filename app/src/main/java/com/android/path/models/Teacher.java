package com.android.path.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher {
    public String name;
    public String phNum;
    public String email;
    public String loginId;
    public ArrayList<Subject> subjects;
    public HashMap<String, Object> miscInfo;

    public Teacher() {
        // Default constructor required for calls to DataSnapshot.getValue(Teacher.class)
    }

    public Teacher(String name, String phNum, String email, String loginId, ArrayList<Subject> subjects,
                   HashMap<String, Object> miscInfo) {
        this.name = name;
        this.phNum = phNum;
        this.email = email;
        this.loginId = loginId;
        this.subjects = subjects;
        this.miscInfo = miscInfo;
    }
}
