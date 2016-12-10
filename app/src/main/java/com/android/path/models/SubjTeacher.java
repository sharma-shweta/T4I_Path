package com.android.path.models;

import java.util.ArrayList;
import java.util.HashMap;

public class SubjTeacher {

    public String subjectId;
    public String teacherId;

    public SubjTeacher() {
        // Default constructor required for calls to DataSnapshot.getValue(School.class)
    }

    public SubjTeacher(String subjectId, String teacherId) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }
}