package com.android.path.models;

import com.android.path.ClassroomStudentsActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SetUpFirebaseData {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public void data() {

        //Academic Years
        Calendar calendar = new GregorianCalendar(2015, 3, 1);
        Date start = calendar.getTime();
        calendar = new GregorianCalendar(2016, 2, 29);
        Date end = calendar.getTime();
        AcademicYear ay = new AcademicYear("2015-2016", start, end);

        //Subjects
        Subject sub1 = new Subject("English");
        Subject sub2 = new Subject("Mathematics");
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        subjects.add(sub1);
        subjects.add(sub2);

        //Teachers
        Teacher t1 = new Teacher("Prof X", "123456", "profX@school.org", "mockGoogleId",
                subjects, null);
        pushToFB("teachers", "Prof-X", t1);

        //Teacher-Subject map
        ArrayList<SubjTeacher> subjectTeachers = new ArrayList<SubjTeacher>();
        subjectTeachers.add(new SubjTeacher(sub1, t1));
        subjectTeachers.add(new SubjTeacher(sub2, t1));

        String c1_id = "BOMABH01";
        Classroom c1 = new Classroom("3", "A", ay, Shift.MORNING, 7, subjects, subjectTeachers, null);
        pushToFB("classes", c1_id, c1);
        String c2_id = "BOMABH02";
        Classroom c2 = new Classroom("3", "B", ay, Shift.MORNING, 7, subjects, subjectTeachers, null);
        pushToFB("classes", c2_id, c2);
        String c3_id = "BOMABH03";
        Classroom c3 = new Classroom("4", "A", ay, Shift.MORNING, 7, subjects, subjectTeachers, null);
        pushToFB("classes", c3_id, c3);
        String c4_id = "BOMABH04";
        Classroom c4 = new Classroom("4", "B", ay, Shift.MORNING, 7, subjects, subjectTeachers, null);
        pushToFB("classes", c4_id, c4);
        String c5_id = "BOMABH05";
        Classroom c5 = new Classroom("5", "A", ay, Shift.MORNING, 7, subjects, null, null);
        pushToFB("classes", c5_id, c5);
        String c6_id = "BOMABH06";
        Classroom c6 = new Classroom("5", "B", ay, Shift.MORNING, 7, subjects, null, null);
        pushToFB("classes", c2_id, c6);

        //Schools
        ArrayList<Classroom> classes = new ArrayList<>();
        classes.addAll(Arrays.asList(c1, c2, c3, c4, c5));
        School s1 = new School("ACJC", SchoolType.PUBLIC, "India", "Maharashtra", "Mumbai", null, null);
        School s2 = new School("IHS", SchoolType.PRIVATE, "India", "Tamil Nadu", "Chennai", null, null);
        School s3 = new School("DPS-B", SchoolType.PUBLIC, "India", "Maharashtra", "Mumbai", null, null);
        School s4 = new School("DPS-A", SchoolType.PUBLIC, "India", "Maharashtra", "Mumbai", classes, null);
        pushToFB("schools", "ACJC", s1);
        pushToFB("schools", "IHS", s2);
        pushToFB("schools", "DPS-B", s3);
        pushToFB("schools", "DPS-A", s4);
    }

    public void pushToFB(String path, String id, Object o) {
        mDatabase.child("path").child(path).child(id).setValue(o);
    }

//    public static void main(String[] args){
//        new SetUpFirebaseData().data();
//    }
}

