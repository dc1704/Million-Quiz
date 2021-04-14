package com.dhruv.millionquiz;

import androidx.annotation.NonNull;

public class Category {

    public static final int PROGRAMMING= 1;
    public static final int SPORTS = 2;
    public static final int INDIAN_HISTORY = 3;
    public static final int GEOGRAPHY = 4;
    public static final int WORLD_POLITY = 5;
    public static final int INDIAN_POLITY = 6;
    public static final int SCIENCE = 7;
    public static final int MATH = 8;

    private int id;
    private String name;

    public Category(){
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
