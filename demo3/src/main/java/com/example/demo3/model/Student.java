package com.example.demo3.model;

public class Student {
    private int id;
    private String name;
    private int gender;
    private double point;
    private Class_student  clazz;
    public Student(int id, String name, int gender, double point, Class_student clazz ) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.point = point;
        this.clazz = clazz;
    }




    public Student(String name, int gender, double point, Class_student clazz ) {
        this.name = name;
        this.gender = gender;
        this.point = point;
        this.clazz = clazz ;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPoint() {
        return point;
    }
    public void setPoint(double point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class_student getClazz() {
        return clazz;
    }

    public void setClazz(Class_student clazz) {
        this.clazz = clazz;
    }
}
