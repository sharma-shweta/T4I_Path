package com.android.path.models;

public class Subject {
    public String name;

    public Subject() {
        // Default constructor required for calls to DataSnapshot.getValue(Subject.class)
    }

    public Subject(String name) {
        this.name = name;
    }
}
