package com.android.path.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Student {

    public String name;
    public int rollNum;
    public Gender gender;
    public String dobStr;
    public int yoi;
    public ArrayList<String> classroomIds;

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(Student.class)
    }

    public Student(String name, int rollNum, Gender gender, String dobStr, int yoi, ArrayList<String> classroomIds) {
        this.name = name;
        this.rollNum = rollNum;
        this.gender = gender;
        this.dobStr = dobStr;
        this.yoi = yoi;
        this.classroomIds = classroomIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNum() {
        return rollNum;
    }

    public void setRollNum(int rollNum) {
        this.rollNum = rollNum;
    }
}
