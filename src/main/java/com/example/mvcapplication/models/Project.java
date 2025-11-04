package com.example.mvcapplication.models;

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
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", description='" + description + '\'' +
                '}';
    }
}