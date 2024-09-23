package com.example.demo3.repository.Student_repo;
import com.example.demo3.model.ClassName;
import com.example.demo3.model.Student;
import java.util.List;

public interface IStudentRepo {
    List<Student> searchStudentsByName(String name);
    boolean CheckEmail (String email);
    List<Student> findAll();
    List<ClassName> findAllClass();
    void addNewStudent(Student student);
    void deleteStudent(int id);
    List<Student> getStudentById(int id);
    void save(Student student);
}
