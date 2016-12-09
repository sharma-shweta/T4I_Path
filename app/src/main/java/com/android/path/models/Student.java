package com.android.path.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Student {

    public String name;
    public int rollNum;
    public Gender gender;
    public Date dob;
    public int yoi;
    public ArrayList<Classroom> classrooms;
    public HashMap<String, Object> miscInfo;

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(Student.class)
    }

    public Student(String name, int rollNum, Gender gender, Date dob, int yoi, ArrayList<Classroom> classrooms,
                   HashMap<String, Object> miscInfo) {
        this.name = name;
        this.rollNum = rollNum;
        this.gender = gender;
        this.dob = dob;
        this.yoi = yoi;
        this.classrooms = classrooms;
        this.miscInfo = miscInfo;
    }
}
