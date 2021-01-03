package com.myoldschool.manager.api;

import javax.persistence.Entity;

@Entity
public class ConcatedNameRollNum {
    private final String name;

    public ConcatedNameRollNum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
