package com.example.demo3.controller;

import com.example.demo3.model.ClassName;
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

@WebServlet(name = "customerServlet", value = "/student-servlet")
public class customerServlet extends HttpServlet {
    private final IStudentService iStudentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                deleteStudent(request, response);
                break;
            default:
                findAll(request, response);
        }
    }


    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        List<Student> studentList = iStudentService.getStudentById(id);

        List<ClassName> classList = iStudentService.findAllClass();
        request.setAttribute("listU",classList );

        if (!studentList.isEmpty()) {
            Student s = studentList.get(0);
            request.setAttribute("st", s);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ClassName> list = iStudentService.findAllClass();
        request.setAttribute("listC", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("search");
        if( name == null) name="";
        List<Student> list = iStudentService.searchStudentsByName(name);
        request.setAttribute("studentList", list);
        request.setAttribute("cl", iStudentService.findAllClass());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("sid"));
        iStudentService.deleteStudent(id);
        response.sendRedirect("student-servlet");
    }

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
            default:
                findAll(request, response);
        }
    }

    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int gender = Integer.parseInt(request.getParameter("gender"));
        double point = Double.parseDouble(request.getParameter("point"));
        int classId = Integer.parseInt(request.getParameter("class_id"));

        if (iStudentService.CheckEmail(email)){
            request.setAttribute("errorMessage", "Email đã tồn tại");
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("point", point);
            request.setAttribute("class_id", classId);
            List<ClassName> list = iStudentService.findAllClass();
            request.setAttribute("listC", list);
            request.getRequestDispatcher("create.jsp").forward(request, response);
            return;
        }
        if (regaxName(name)) {
            request.setAttribute("errorMessage", "Tên chỉ chứa chữ, không có số và ký tự đặc biệt.");
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("point", point);
            request.setAttribute("class_id", classId);
            List<ClassName> list = iStudentService.findAllClass();
            request.setAttribute("listC", list);
            request.getRequestDispatcher("create.jsp").forward(request, response);
            return;
        }

        ClassName clazz = new ClassName(classId);
        Student student = new Student(name,email,gender, point, clazz);
        iStudentService.addNewStudent(student);
        response.sendRedirect("student-servlet");
    }

    private boolean regaxName(String name) {
        return !name.matches("^[\\p{L}\\s]{1,50}$");
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int gender = Integer.parseInt(request.getParameter("gender"));
            double point = Double.parseDouble(request.getParameter("point"));
            int classId = Integer.parseInt(request.getParameter("class_id"));

            if (iStudentService.CheckEmail(email)){
                request.setAttribute("errorMessage", "Email đã tồn tại");
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("gender", gender);
                request.setAttribute("point", point);
                request.setAttribute("class_id", classId);
                List<ClassName> list = iStudentService.findAllClass();
                request.setAttribute("listU", list);
                request.getRequestDispatcher("update.jsp").forward(request, response);
                return;
            }

            if (regaxName(name)) {
                request.setAttribute("errorMessage", "Tên chỉ chứa chữ, không có số và ký tự đặc biệt.");
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("gender", gender);
                request.setAttribute("point", point);
                request.setAttribute("class_id", classId);
                List<ClassName> list = iStudentService.findAllClass();
                request.setAttribute("listU", list);
                request.getRequestDispatcher("update.jsp").forward(request, response);
                return;
            }
            ClassName clazz = new ClassName(classId);
            Student student = new Student(id, name, email, gender, point, clazz);
            iStudentService.save(student);

            response.sendRedirect("student-servlet?");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}