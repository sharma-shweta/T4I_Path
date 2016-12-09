package com.android.path.models;

import java.util.Date;

public class AcademicYear {
    public String ayName;
    public Date startDate;
    public Date endDate;

    public AcademicYear() {
        // Default constructor required for calls to DataSnapshot.getValue(AcademicYear.class)
    }

    public AcademicYear(String ayName, Date startDate, Date endDate) {
        this.ayName = ayName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

