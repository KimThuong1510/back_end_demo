package com.example.demo3.controller;

import com.example.demo3.model.Class_student;
import com.example.demo3.model.Student;
import com.example.demo3.service.IStudentService;
import com.example.demo3.service.StudentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name= "customerServlet", value="/student-servlet")
public class customerServlet extends HttpServlet {
    private final IStudentService iStudentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showUpdateForm(request, response);
                break;
            case "delete":
                deleteStudent(request,response);
                break;
            default:
                findAll(request, response);
        }
        findAll(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("sid"));

        // Gọi phương thức getStudentById và lưu trữ kết quả
        List<Student> studentList = iStudentService.getStudentById(id);

        // Nếu danh sách không rỗng, lấy phần tử đầu tiên (vì ID là duy nhất)
        if (!studentList.isEmpty()) {
            Student s = studentList.get(0);
            request.setAttribute("st", s);
        }

        // Điều hướng đến trang JSP hiển thị chi tiết sinh viên
        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("studentList", iStudentService.findAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(request, response);
    }
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("sid"));
        iStudentService.deleteStudent(id);
    }
//
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addNewStudent(request, response);
                break;
            case "edit":
                save(request, response);
                break;
            case "delete":
                break;
            default:
                findAll(request, response);
        }

        findAll(request, response);
    }

    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        double point = Double.parseDouble(request.getParameter("point"));
        int classId = Integer.parseInt(request.getParameter("class_id"));
        Class_student clazz = new Class_student(classId);

        Student student = new Student(name, gender, point, clazz);
        iStudentService.addNewStudent(student);
    }

    private void save(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        double point = Double.parseDouble(request.getParameter("point"));
        int classInt  = Integer.parseInt(request.getParameter("class_id"));
        Class_student clazz = new Class_student(classInt);
        Student student = new Student(id, name, gender, point, clazz);
        iStudentService.save(student);

    }
}
