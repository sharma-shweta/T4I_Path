package com.android.path.models;

import java.util.ArrayList;

public class StudentAnalytics {
    public String name;
    public int rollNum;
    public double oldGPA;
    public double newGPA;

    public StudentAnalytics() {
    }

    public StudentAnalytics(String name, int rollNum, double oldGPA, double newGPA) {
        this.name = name;
        this.rollNum = rollNum;
        this.oldGPA = oldGPA;
        this.newGPA = newGPA;
    }
}
