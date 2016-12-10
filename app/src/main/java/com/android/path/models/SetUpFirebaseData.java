package com.android.path.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SetUpFirebaseData {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public void data() {

        //Academic Years
        Calendar calendar = new GregorianCalendar(2015, 3, 1);
        Date start = calendar.getTime();
        calendar = new GregorianCalendar(2016, 2, 29);
        Date end = calendar.getTime();
        AcademicYear ay = new AcademicYear("2015-2016", start, end);
        pushToFB("academic-years", "2015-2016", ay);

        //Subjects
        Subject sub1 = new Subject("English");
        Subject sub2 = new Subject("Mathematics");
        pushToFB("subjects", "english", sub1);
        pushToFB("subjects", "mathematics", sub2);
        ArrayList<String> subjectIds = new ArrayList<String>();
        subjectIds.add("english");
        subjectIds.add("mathematics");

        //Teachers
        Teacher t1 = new Teacher("Prof X", "123456", "profX@school.org", "mockGoogleId", subjectIds);
        pushToFB("teachers", "Prof-X", t1);

        //Teacher-Subject map
        ArrayList<SubjTeacher> subjectTeachers = new ArrayList<SubjTeacher>();
        subjectTeachers.add(new SubjTeacher("english", "Prof-X"));
        subjectTeachers.add(new SubjTeacher("mathematics", "Prof-X"));

        String c1_id = "class1";
        Classroom c1 = new Classroom("3", "A", "2015-2016", Shift.MORNING, 7, subjectIds, subjectTeachers, new ArrayList<String>());
        pushToFB("classes", c1_id, c1);
        String c2_id = "class2";
        Classroom c2 = new Classroom("3", "B", "2015-2016", Shift.MORNING, 7, subjectIds, subjectTeachers, new ArrayList<String>());
        pushToFB("classes", c2_id, c2);
        String c3_id = "class3";
        Classroom c3 = new Classroom("4", "A", "2015-2016", Shift.MORNING, 7, subjectIds, subjectTeachers, new ArrayList<String>());
        pushToFB("classes", c3_id, c3);
        String c4_id = "class4";
        Classroom c4 = new Classroom("4", "B", "2015-2016", Shift.MORNING, 7, subjectIds, subjectTeachers, new ArrayList<String>());
        pushToFB("classes", c4_id, c4);
        String c5_id = "class5";
        Classroom c5 = new Classroom("5", "A", "2015-2016", Shift.MORNING, 7, subjectIds, new ArrayList<SubjTeacher>(), new ArrayList<String>());
        pushToFB("classes", c5_id, c5);
        String c6_id = "class6";
        Classroom c6 = new Classroom("5", "B", "2015-2016", Shift.MORNING, 7, subjectIds, new ArrayList<SubjTeacher>(), new ArrayList<String>());
        pushToFB("classes", c6_id, c6);

        //List of classrooms
        ArrayList<String> classIdsList1 = new ArrayList<String>();
        classIdsList1.addAll(Arrays.asList(c1_id, c2_id, c3_id, c4_id));
        ArrayList<String> classIdsList2 = new ArrayList<String>();
        classIdsList2.addAll(Arrays.asList(c5_id, c6_id));

        //Schools
        School s1 = new School("ACJC", SchoolType.PUBLIC, "India", "Maharashtra", "Mumbai", classIdsList1);
        School s2 = new School("IHS", SchoolType.PRIVATE, "India", "Tamil Nadu", "Chennai", new ArrayList<String>());
        School s3 = new School("DPS-B", SchoolType.PUBLIC, "India", "Maharashtra", "Mumbai", new ArrayList<String>());
        School s4 = new School("DPS-A", SchoolType.PUBLIC, "India", "Maharashtra", "Mumbai", classIdsList2);
        pushToFB("schools", s1.name, s1);
        pushReverseLocationToFB(s1);
        pushToFB("schools", s2.name, s2);
        pushReverseLocationToFB(s2);
        pushToFB("schools", s3.name, s3);
        pushReverseLocationToFB(s3);
        pushToFB("schools", s4.name, s4);
        pushReverseLocationToFB(s4);
    }

    public void pushToFB(String path, String id, Object o) {
        mDatabase.child("path").child(path).child(id).setValue(o);
    }

    public void pushReverseLocationToFB(School s) {
        mDatabase.child("path").child("school-locations").child(s.country).child(s.state).
                child(s.city).child(s.name).setValue(s.name);
    }

}

