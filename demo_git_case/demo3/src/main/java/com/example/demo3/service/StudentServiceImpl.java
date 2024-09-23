package com.example.demo3.service;

import com.example.demo3.model.ClassName;
import com.example.demo3.model.Student;
import com.example.demo3.repository.Student_repo.IStudentRepo;
import com.example.demo3.repository.Student_repo.StudentRepoImpl;

import java.util.List;

public class StudentServiceImpl implements IStudentService{
    private final IStudentRepo iStudentRepo = new StudentRepoImpl();

    @Override
    public List<Student> findAll() {
        return iStudentRepo.findAll() ;
    }

    @Override
    public boolean CheckEmail(String email) {
        return iStudentRepo.CheckEmail(email);
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return iStudentRepo.searchStudentsByName(name);
    }

    @Override
    public List<ClassName> findAllClass() {
        return iStudentRepo.findAllClass();
    }

    @Override
    public void addNewStudent(Student student) {
        iStudentRepo.addNewStudent(student);
    }

    @Override
    public void deleteStudent(int id) {
        iStudentRepo.deleteStudent(id);
    }

    @Override
    public List<Student> getStudentById(int id) {
        return iStudentRepo.getStudentById(id);
    }

    @Override
    public void save(Student student) {
        iStudentRepo.save(student);
    }
}
