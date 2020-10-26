package com.myoldschool.manager.api;

public class Student {
    private final int id;
    private final String name;
    private final String marks;
    private final int rollno;

    public Student(int id, String name,int rollno, String marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.rollno = rollno;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMarks() {
        return marks;
    }

    public int getRollno() {
        return rollno;
    }
}
