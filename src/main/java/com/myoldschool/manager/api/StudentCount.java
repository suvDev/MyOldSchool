package com.myoldschool.manager.api;

public class StudentCount {
    private final int id;
    private final String names;
    private final int count;

    public StudentCount(int id, String names,int count){
        this.id = id;
        this.names = names;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
