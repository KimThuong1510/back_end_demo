package com.example.demo3.service;

import com.example.demo3.model.ClassName;
import com.example.demo3.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    boolean CheckEmail (String email);
    List<Student> searchStudentsByName(String name);
    List<ClassName> findAllClass();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudentById( int id);
    void save(Student student);
}


