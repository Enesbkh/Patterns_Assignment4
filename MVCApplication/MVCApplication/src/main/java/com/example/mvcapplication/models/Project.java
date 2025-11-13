package com.example.mvcapplication.models;

import com.example.mvcapplication.connectorMannager.ConnectionConnector;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Objects;

public class Project {
    private final IntegerProperty projectID;
    private final StringProperty description;

    public Project(int projectID, String description) {
        this.projectID = new SimpleIntegerProperty(projectID);
        this.description = new SimpleStringProperty(description);
    }

    public IntegerProperty getProjectIDProperty() {
        return projectID;
    }

    public int getProjectID(){
        return this.projectID.get();
    }

    public void setProjectID(int projectID) {
        this.projectID.set(projectID);
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public String getDescription(){
        return this.description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    };

    public static ObservableList<Project> getAllProjects(){
        ObservableList<Project> projectData = FXCollections.observableArrayList();
        String query = "SELECT * FROM Project";

        try (Connection conn = ConnectionConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("ProjectID");
                String name = rs.getString("ProjectDescription");
                projectData.add(new Project(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectData;

    }

    public static ObservableList<Project> searchProject(String searched){
        ObservableList<Project> projectResult = FXCollections.observableArrayList();
        String query = "SELECT * FROM Project where ProjectID like ?";

        try {
            Connection conn = ConnectionConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,"%" + searched + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                projectResult.add(new Project(rs.getInt("ProjectID"),
                        rs.getString("ProjectDescription")));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getClass());
        }
        return projectResult;    }

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