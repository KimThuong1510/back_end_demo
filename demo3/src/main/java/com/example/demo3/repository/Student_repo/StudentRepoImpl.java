package com.example.demo3.repository.Student_repo;

import com.example.demo3.model.ClassName;
import com.example.demo3.model.Student;
import com.example.demo3.repository.BaseRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentRepoImpl implements IStudentRepo {
    private final BaseRepository baseRepository;
    {
        try {
            baseRepository = new BaseRepository();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String FIND_ALL = "select id, name, email, gender, point,  class_name \n" +
            "from class join student on class.class_id = student.class_id;\n";
    private static final String FIND_ALL_CLASS = "select * from class";
    private static final String INSERT_STUDENT =
            "insert into student(name, email, gender, point, class_id)\n" +
                    "values(?,?,?,?,?)";
    private static final String DELETE_STUDENT =
            "delete from student\n" +
                    "where id = ?;" ;
    private static final String GETID_STUDENT = "select s.id, s.name, s.email, s.gender, s.point, c.class_id,c.class_name\n" +
            "from student s\n" +
            "join class c on s.class_id = c.class_id\n" +
            "where s.id = ?";
    private static final String UPDATE_STUDENT =
            "update student\n" +
                    "set name = ?,\n" +
                    "email = ?, \n" +
                    "gender = ?,\n" +
                    "point = ?,\n" +
                    "class_id = ?\n"+
                    "where id = ?;";
    private static final String FindByName ="select id, name, email, gender, point,  class_name, class.class_id\n" +
            "from class join student on class.class_id = student.class_id\n" +
            "where student.name like ?";
    private static final String CheckEmail = "select count(*) \n" +
            "from student \n" +
            "where email = ?;";


    @Override
    public List<Student> searchStudentsByName(String name) {
        Connection connection = baseRepository.getConnection();
        List<Student> list = new ArrayList<>();
        try {
            PreparedStatement pre = connection.prepareStatement(FindByName);
            pre.setString(1,"%"+name+"%");
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                name = resultSet.getString("name");
                String email =  resultSet.getString("email");
                int gender = resultSet.getInt("gender");
                double point = resultSet.getDouble("point");
                int class_id = resultSet.getInt("class_id");
                String class_name = resultSet.getString("class_name");
                ClassName clazz = new ClassName(class_id, class_name);
                list.add(new Student(id, name,email, gender, point, clazz));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean checkEmail(String email) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement pre = connection.prepareStatement(CheckEmail);
            pre.setString(1,email);
            ResultSet res = pre.executeQuery();
            if(res.next()){
                return res.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    @Override
    public List<Student> findAll() {
        Connection connection = baseRepository.getConnection();
        List<Student> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement(); // trả về dữ liệu
            ResultSet resultSet = statement.executeQuery(FIND_ALL); // trả vè bảng

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int gender = resultSet.getInt("gender");
                double point = resultSet.getDouble("point");
                int class_id = resultSet.getInt("class_id");
                String class_name = resultSet.getString("class_name");
                ClassName clazz = new ClassName(class_id, class_name);
                list.add(new Student(id, name,email, gender, point, clazz));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<ClassName> findAllClass() {
        Connection connection = baseRepository.getConnection();
        List<ClassName> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CLASS);

            while (resultSet.next()){
                int id = resultSet.getInt("class_id");
                String name = resultSet.getString("class_name");
                ClassName clazz = new ClassName(id, name);
                list.add(clazz);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void addNewStudent(Student student) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_STUDENT);
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getGender());
            ps.setDouble(4, student.getPoint());
            ps.setInt(5, student.getClazz().getClass_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(int id) {
        Connection connection = baseRepository.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_STUDENT);
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Student getStudentById(int id) {
        Connection connection = baseRepository.getConnection();
        Student students = null;
        try {
            PreparedStatement ps = connection.prepareStatement(GETID_STUDENT);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            List<Student> list = new ArrayList();
            while (resultSet.next()){
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int gender = resultSet.getInt("gender");
                double point = resultSet.getDouble("point");
                int class_id = resultSet.getInt("class_id");
                ClassName clazz = new ClassName(class_id);
                list.add(new Student(id1 , name, email, gender, point,clazz));
            }
            if(!list.isEmpty()){
                students = list.get(0);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return students;
    }

    @Override
    public void save(Student student) {
        try {
            Connection connection = baseRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENT);
            ps.setString(1, student.getName());
            ps.setString(2,student.getEmail());
            ps.setInt(3, student.getGender());
            ps.setDouble(4, student.getPoint());
            ps.setInt(5, student.getClazz().getClass_id());
            ps.setInt(6, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
