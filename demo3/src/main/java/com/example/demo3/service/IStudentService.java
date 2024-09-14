package com.example.demo3.service;

import com.example.demo3.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudentById( int id);
    void save(Student student);
}


