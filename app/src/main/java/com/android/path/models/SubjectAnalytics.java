package com.android.path.models;

import java.util.HashMap;
import java.util.Map;

public class SubjectAnalytics {
    public String name;
    public Double goal;
    public Double achieved;
    public Map<Double, Double> gpaStudentRatioMap = new HashMap<>();

    public SubjectAnalytics() {
        // Default constructor required for calls to DataSnapshot.getValue(FBStudent.class)
    }

    public SubjectAnalytics(String name, Double goal, Double achieved, Map<Double, Double> gpaStudentRatioMap) {
        this.name = name;
        this.goal = goal;
        this.achieved = achieved;
        this.gpaStudentRatioMap = gpaStudentRatioMap;
    }
}
