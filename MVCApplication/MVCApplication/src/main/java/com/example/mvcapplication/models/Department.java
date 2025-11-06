package com.example.mvcapplication.models;

import com.example.mvcapplication.connectorMannager.ConnectionConnector;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Department {
    private  int DepartmentId;
    private String DepartmentName;

    public Department(int departmentId, String departmentName) {
        DepartmentId = departmentId;
        DepartmentName = departmentName;
    }
    public int getDepartmentId() {return DepartmentId;}
    public String getDepartmentName() {return DepartmentName;}

    public void setDepartmentId(int departmentId) {DepartmentId = departmentId;}
    public void setDepartmentName(String departmentName) {DepartmentName = departmentName;}

        //Use the DB to get the data(all the departments)
        public static ObservableList<Department> getAllDepartments() {
        ObservableList<Department> departmentData = FXCollections.observableArrayList();

        String query = "SELECT department_id, department_name FROM departments";

        try (Connection conn = ConnectionConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("department_id");
                String name = rs.getString("department_name");
                departmentData.add(new Department(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentData;
    }



}