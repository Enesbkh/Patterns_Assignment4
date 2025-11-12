package com.example.mvcapplication.models;

import com.example.mvcapplication.connectorMannager.ConnectionConnector;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Objects;

public class Department {

    private final IntegerProperty departmentId;
    private final StringProperty departmentName;

    public Department(int departmentId, String departmentName) {
        this.departmentId = new SimpleIntegerProperty(departmentId);
        this.departmentName = new SimpleStringProperty(departmentName);
    }

    // Property Getters (used by TableView)
    public IntegerProperty departmentIdProperty() {return departmentId;}
    public StringProperty departmentNameProperty() {return departmentName;}

    //Normal Getters and Setters
    public int getDepartmentId() {return departmentId.get();}
    public String getDepartmentName() {return departmentName.get();}

    public void setDepartmentId(int id) {this.departmentId.set(id);}
    public void setDepartmentName(String name) {this.departmentName.set(name);}


    //Get ALL the departments
    public static ObservableList<Department> getAllDepartments() {
        ObservableList<Department> departmentData = FXCollections.observableArrayList();
        String query = "SELECT DepartmentID, DepartmentName FROM Department";

        try (Connection conn = ConnectionConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("DepartmentID");
                String name = rs.getString("DepartmentName");
                departmentData.add(new Department(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentData;
    }

    //Search Departments
    public static ObservableList<Department> searchDepartments(String searched) {
        ObservableList<Department> departmentResult = FXCollections.observableArrayList();
        String query = "SELECT * FROM Department WHERE DepartmentName LIKE ?";

        try (Connection conn = ConnectionConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + searched + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                departmentResult.add(new Department(
                        rs.getInt("DepartmentID"),
                        rs.getString("DepartmentName")
                ));
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departmentResult;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentId, that.departmentId) && Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName=" + departmentName +
                '}';
    }
}
