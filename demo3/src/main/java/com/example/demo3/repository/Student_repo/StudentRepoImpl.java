package com.example.demo3.repository.Student_repo;

import com.example.demo3.model.Class_student;
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
    private static final String FIND_ALL = "select s.id, s.name, s.gender, s.point, c.class_id, c.class_name \n" +
            "from class c join student s on c.class_id = s.class_id \n";
    private static final String INSERT_STUDENT =
            "insert into student(name, gender, point, class_id)\n" +
                    "values(?,?,?,?)";
    private static final String DELETE_STUDENT =
            "delete from student\n" +
                    "where id = ?;" ;
    private static final String GETID_STUDENT =
            "select * from student\n" +
                    "where id = ?;";
    private static final String UPDATE_STUDENT =
            "update student\n" +
                    "set name = ?,\n" +
                    "gender = ?,\n" +
                    "point = ?,\n" +
                    "class_id = ?\n"+
                    "where id = ?;";
    private static final String CALL_INSERT =
            "call insert_student(?,?,?,?)" ;

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
                int gender = resultSet.getInt("gender");
                double point = resultSet.getDouble("point");
                int class_id = resultSet.getInt("class_id");
                String class_name = resultSet.getString("class_name");
                Class_student clazz = new Class_student(class_id, class_name);
                list.add(new Student(id, name, gender, point, clazz));
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
            ps.setInt(2, student.getGender());
            ps.setDouble(3, student.getPoint());
            ps.setInt(4, student.getClazz().getClass_id());
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
    public List<Student> getStudentById(int id) {
        Connection connection = baseRepository.getConnection();
        List<Student> listId = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(GETID_STUDENT);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int gender = resultSet.getInt("gender");
                double point = resultSet.getDouble("point");
                int class_id = resultSet.getInt("class_id");
                String class_name = resultSet.getString("class_name");
                Class_student clazz = new Class_student(class_id,class_name );
                listId.add(new Student(id1 ,  name, gender, point,clazz));
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return listId;
    }

    @Override
    public void save(Student student) {
        try {
            Connection connection = baseRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENT);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getGender());
            ps.setDouble(3, student.getPoint());
            ps.setInt(4, student.getClazz().getClass_id());
            ps.setInt(5, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
