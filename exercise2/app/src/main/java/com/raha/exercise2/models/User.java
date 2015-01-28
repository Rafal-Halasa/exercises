package com.raha.exercise2.models;

import com.raha.exercise2.enums.Gender;

/**
 * Created by raha on 2015-01-28.
 */
public class User {
    String firstNameText;
    String secondNameText;
    boolean marriedChecked;
    Gender gender;
    int day;
    int month;
    int year;

    public User(String firstNameText, String secondNameText, boolean marriedChecked, Gender gender, int day, int month, int year) {
        this.firstNameText = firstNameText;
        this.secondNameText = secondNameText;
        this.marriedChecked = marriedChecked;
        this.gender = gender;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getFirstNameText() {
        return firstNameText;
    }

    public String getSecondNameText() {
        return secondNameText;
    }

    public boolean isMarriedChecked() {
        return marriedChecked;
    }

    public Gender getGender() {
        return gender;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
