package com.example.mvcapplication.views;


import com.example.mvcapplication.controllers.EmployeeController;
import com.example.mvcapplication.models.Employee;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class EmployeeView extends VBox {
    private final TableView<Employee> tableView;
    private final EmployeeController controller;


    public EmployeeView(EmployeeController controller) throws SQLException {
        this.controller = controller;
        this.tableView = new TableView<>();
        this.createTable();
        this.getChildren().addAll(createSearchBox(),tableView);
        this.bindTableData();
        this.createSearchBox();
    }

    private void createTable() {
        TableColumn<Employee, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employee, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        tableView.getColumns().addAll(firstNameCol, lastNameCol, salaryCol);
    }

    private void bindTableData() throws SQLException {
        tableView.setItems(controller.getEmployees());
    }

    private HBox createSearchBox(){
        Label searchLabel = new Label("First Name");
        TextField searchTextField = new TextField();
        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            String searched = searchTextField.getText();
            tableView.setItems(controller.searchEmployee(searched));
        });

        HBox searchBox = new HBox(10,searchLabel,searchTextField,searchButton);
        return searchBox;
    }

}