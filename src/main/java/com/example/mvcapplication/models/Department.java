package com.example.mvcapplication.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Department {
    private  int DepartmentId;
    private String DepartmentName;


    public Department(int departmentId, String departmentName) {
        DepartmentId = departmentId;
        DepartmentName = departmentName;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    //NEEDS TO BE CALLED FROM DB
    public static ObservableList<Department> getAllDepartments(){
        ObservableList<Department> departmentsData = FXCollections.observableArrayList(
                new Department(1 ,"Technology"),
                new Department(2,"Buisness"),
                new Department( 3,"Accounting")
        );
        return departmentsData;
    }



}