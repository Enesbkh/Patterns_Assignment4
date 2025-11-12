package com.example.mvcapplication.models;

import java.util.Objects;

public class Project {
    private int projectID;
    private String description;

    public Project(int projectID, String description) {
        this.projectID = projectID;
        this.description = description;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectID == project.projectID && Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, description);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", description='" + description + '\'' +
                '}';
    }

 }