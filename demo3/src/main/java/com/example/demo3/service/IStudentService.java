package com.example.demo3.service;

import com.example.demo3.model.ClassName;
import com.example.demo3.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    boolean checkEmail (String email);
    List<Student> searchStudentsByName(String name);
    List<ClassName> findAllClass();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    Student getStudentById( int id);
    void save(Student student);
}


