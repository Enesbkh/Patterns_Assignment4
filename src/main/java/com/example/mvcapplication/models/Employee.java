package com.example.mvcapplication.models;

import com.example.mvcapplication.connectorMannager.ConnectionConnector;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {

    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final DoubleProperty salary;
    private final IntegerProperty departmentId;


    public Employee(int id, String firstName, String lastName, double salary, int departmentId) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.salary = new SimpleDoubleProperty(salary);
        this.departmentId = new SimpleIntegerProperty(departmentId);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public IntegerProperty departmentIdProperty() {return departmentId;}

    //READ all employees from DB
    public static ObservableList<Employee> getAllEmployees() {
        ObservableList<Employee> employeeData = FXCollections.observableArrayList();

        String query = "SELECT employee_id, first_name, last_name, salary, department_id FROM employees";

        try (Connection conn = ConnectionConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                double salary = rs.getDouble("salary");
                int deptId = rs.getInt("department_id");

                employeeData.add(new Employee(id, firstName, lastName, salary, deptId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeData;
    }
}