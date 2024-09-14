package com.example.demo3.repository.Student_repo;


import com.example.demo3.model.Student;

import java.util.List;

public interface IStudentRepo {
    List<Student> findAll();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudentById(int id);
    void save(Student student);
}
