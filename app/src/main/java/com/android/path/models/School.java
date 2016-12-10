package com.android.path.models;

import java.util.ArrayList;
import java.util.HashMap;

public class School {
    public String name;
    public SchoolType st;
    public String country;
    public String state;
    public String city;
    public ArrayList<String> classIds;

    public School() {
        // Default constructor required for calls to DataSnapshot.getValue(School.class)
    }

    public School(String name, SchoolType st, String country, String state, String city,
                  ArrayList<String> classIds) {
        this.name = name;
        this.st = st;
        this.country = country;
        this.city = city;
        this.state = state;
        this.classIds = classIds;
    }
}
