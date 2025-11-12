package com.example.mvcapplication.views;

import com.example.mvcapplication.controllers.DepartmentController;
import com.example.mvcapplication.models.Department;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DepartmentView extends VBox {
    private final TableView<Department> tableView;
    private final DepartmentController controller;

    public DepartmentView(DepartmentController controller) {
        this.controller = controller;
        this.tableView = new TableView<>();
        this.createTable();
        this.getChildren().addAll(createSearchBox(), tableView); // 🔥 search box added here
        this.bindTableData();
    }

    private void createTable() {
        TableColumn<Department, Integer> idCol = new TableColumn<>("Department ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("departmentId"));

        TableColumn<Department, String> nameCol = new TableColumn<>("Department Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("departmentName"));

        tableView.getColumns().addAll(idCol, nameCol);
    }

    private void bindTableData() {
        tableView.setItems(controller.getDepartments());
    }

    private HBox createSearchBox(){
        Label searchLabel = new Label("Department ID: ");
        TextField searchTextField = new TextField();
        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            String searched = searchTextField.getText();
            tableView.setItems(controller.searchDepartments(searched));
        });
        HBox searchBox = new HBox(10,searchLabel,searchTextField,searchButton);
        return searchBox;
    }
}
