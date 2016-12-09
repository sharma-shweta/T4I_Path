package com.android.path.models;

import java.util.ArrayList;

public class Classroom {

    public String grade;
    public String section;
    public AcademicYear ay;
    public Shift shift;
    public int hours;
    public ArrayList<Subject> subjects;
    public ArrayList<SubjTeacher> subjTeachers;
    public ArrayList<Student> students;

    public Classroom() {
        // Default constructor required for calls to DataSnapshot.getValue(Classroom.class)
    }

    public Classroom(String grade, String section, AcademicYear ay, Shift shift,
                     int hours, ArrayList<Subject> subjects, ArrayList<SubjTeacher> subjTeachers,
                     ArrayList<Student> students) {
        this.grade = grade;
        this.section = section;
        this.ay = ay;
        this.shift = shift;
        this.hours = hours;
        this.subjects = subjects;
        this.subjTeachers = subjTeachers;
        this.students = students;
    }

}
