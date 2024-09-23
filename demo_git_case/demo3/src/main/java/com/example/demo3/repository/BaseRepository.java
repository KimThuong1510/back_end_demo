package com.example.demo3.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class BaseRepository {
    private final Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/NHAPHOC24";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "codegym";

    public BaseRepository() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Kết nối thành công / Connection success");
    }
    public Connection getConnection(){
        return connection;
    }

}
