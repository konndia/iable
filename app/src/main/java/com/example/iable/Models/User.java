package com.example.iable.Models;

public class User {
    private String email_registration, password_registration, name, height, weight, age;
    int stepsCount;

    public User() {}

    public User(String email_registration, String password_registration, String name, String height, String weight, String age, int stepsCount) {
        this.email_registration = email_registration;
        this.password_registration = password_registration;
        this.name = name;
        this.height= height;
        this.weight = weight;
        this.age = age;
        this.stepsCount = stepsCount;
    }

    public String getEmail() {
        return email_registration;
    }

    public void setEmail(String email) {
        this.email_registration = email;
    }

    public String getPass() {
        return password_registration;
    }

    public void setPass(String pass) {
        this.password_registration = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

//    public int getStepsCount() {
//        return stepsCount;
//    }
//
//    public void setStepsCount(int stepsCount, String steps) {
//        this.stepsCount = stepsCount;
//    }
}
