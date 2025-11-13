package com.example.mvcapplication.controllers;

import com.example.mvcapplication.models.Employee;
import com.example.mvcapplication.models.Project;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ProjectController {

    public ProjectController() {
    }

    public ObservableList<Project> getProjects() throws SQLException {return Project.getAllProjects();}
    public ObservableList<Project> searchProject(String search){
        return Project.searchProject(search);
    }
}
