package com.android.path.models;

import java.util.ArrayList;
import java.util.HashMap;

public class School {
    public String name;
    public SchoolType st;
    public String country;
    public String state;
    public String city;
    public ArrayList<Classroom> classes;
    public HashMap<String, Object> miscInfo;

    public School() {
        // Default constructor required for calls to DataSnapshot.getValue(School.class)
    }

    public School(String name, SchoolType st, String country, String state, String city,
                  ArrayList<Classroom> classes, HashMap<String, Object> miscInfo) {
        this.name = name;
        this.st = st;
        this.country = country;
        this.city = city;
        this.state = state;
        this.classes = classes;
        this.miscInfo = miscInfo;
    }
}
