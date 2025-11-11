package com.example.mvcapplication.connectorMannager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  ConnectionConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/assignment4?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            return null;
        }
    }
}
