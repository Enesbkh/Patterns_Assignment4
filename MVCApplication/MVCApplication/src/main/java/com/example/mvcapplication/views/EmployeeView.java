package com.example.mvcapplication.views;


import com.example.mvcapplication.controllers.EmployeeController;
import com.example.mvcapplication.models.Employee;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class EmployeeView extends VBox {
    private final TableView<Employee> tableView;
    private final EmployeeController controller;


    public EmployeeView(EmployeeController controller) {
        this.controller = controller;
        this.tableView = new TableView<>();
        this.createTable();
        this.getChildren().add(tableView);
        this.bindTableData();
    }

    private void createTable() {

        TableColumn<Employee, String> employeeIDCol = new TableColumn<>("Employee Id");
        employeeIDCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        TableColumn<Employee, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employee, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        tableView.getColumns().addAll(employeeIDCol,firstNameCol, lastNameCol, salaryCol);
    }

    private void bindTableData() {
        tableView.setItems(controller.getEmployees());
    }

    private void createSearchBox(){
        Label searchLabel = new Label("First Name");
        TextField searchTextField = new TextField();
        Button searchButton = new Button("Search");
    }
}