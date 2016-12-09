package com.android.path.models;

import java.util.ArrayList;
import java.util.HashMap;

public class SubjTeacher {

    public Subject subject;
    public Teacher teacher;

    public SubjTeacher() {
        // Default constructor required for calls to DataSnapshot.getValue(School.class)
    }

    public SubjTeacher(Subject s, Teacher t) {
        this.subject = s;
        this.teacher = t;
    }
}