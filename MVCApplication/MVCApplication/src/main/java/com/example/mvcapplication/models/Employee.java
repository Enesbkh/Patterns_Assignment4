package com.example.mvcapplication.models;

import com.example.mvcapplication.connectorMannager.ConnectionConnector;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
    public static ObservableList<Employee> getAllEmployees() throws SQLException {
        ObservableList<Employee> employeeData = FXCollections.observableArrayList();

        String query = "SELECT EmployeeID, FirstName, LastName, Salary, DepartmentID FROM Employee";

        try {
            Connection conn = ConnectionConnector.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("EmployeeID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                double salary = rs.getDouble("Salary");
                int deptId = rs.getInt("DepartmentID");

                employeeData.add(new Employee(id, firstName, lastName, salary, deptId));
            }
        } catch (SQLException e) {
            System.out.println(e.getClass());
        }
        return employeeData;
    }

    public static ObservableList<Employee> searchEmployees(String searched){
        ObservableList<Employee> employeeResult = FXCollections.observableArrayList();
        String query = "SELECT * FROM Employee where FirstName like ?";

        try {
            Connection conn = ConnectionConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,"%" + searched + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                employeeResult.add(new Employee(rs.getInt("EmployeeID"),
                                                rs.getString("FirstName"),
                                                rs.getString("LastName"),
                                                rs.getDouble("Salary"),
                                                rs.getInt("DepartmentId") ));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getClass());
        }
        return employeeResult;
    }
}