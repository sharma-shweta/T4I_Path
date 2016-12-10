package com.android.path.models;

import java.util.ArrayList;

public class Classroom {

    public String grade;
    public String section;
    public String academicYearId;
    public Shift shift;
    public int hours;
    public ArrayList<String> subjectIds;
    public ArrayList<SubjTeacher> subjTeachers;
    public ArrayList<String> studentIds;

    public Classroom() {
        // Default constructor required for calls to DataSnapshot.getValue(Classroom.class)
    }

    public Classroom(String grade, String section, String ayId, Shift shift,
                     int hours, ArrayList<String> subjects, ArrayList<SubjTeacher> subjTeachers,
                     ArrayList<String> students) {
        this.grade = grade;
        this.section = section;
        this.academicYearId = ayId;
        this.shift = shift;
        this.hours = hours;
        this.subjectIds = subjects;
        this.subjTeachers = subjTeachers;
        this.studentIds = students;
    }

}
