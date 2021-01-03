package com.myoldschool.manager.api;

public class StudentCount {
    private final int sid;
    private final String names;
    private final int count;
    private final double marks;

    public StudentCount(int count, double marks, String names, int sid){
        this.sid = sid;
        this.names = names;
        this.count = count;
        this.marks = marks;
    }

    public int getSid() {
        return sid;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }

    public double getMarks() {
        return marks;
    }
}
