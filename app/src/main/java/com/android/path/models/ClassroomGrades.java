package com.android.path.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class ClassroomGrades implements Comparable<ClassroomGrades>{
    public Double gradeLevel;
    public Double percOfStudents;

    public ClassroomGrades() {
        // Default constructor required for calls to DataSnapshot.getValue(ClassroomGrades.class)
    }

    public ClassroomGrades(Double gradeLevel, Double percOfStudents) {
        this.gradeLevel = gradeLevel;
        this.percOfStudents = percOfStudents;
    }

    @Override
    public int compareTo(ClassroomGrades other) {
        return (int) (this.gradeLevel - other.gradeLevel);
    }
}
