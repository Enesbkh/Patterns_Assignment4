package com.example.mvcapplication.controllers;

import com.example.mvcapplication.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class EmployeeController {

    public EmployeeController() {

    }

    public ObservableList<Employee> getEmployees() throws SQLException {
        return Employee.getAllEmployees();
    }

    public ObservableList<Employee> searchEmployee(String search){
        return Employee.searchEmployees(search);
    }

}
