package com.example.mvcapplication.views;

import com.example.mvcapplication.controllers.DepartmentController;
import com.example.mvcapplication.models.Department;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class DepartmentView extends VBox {
    private final TableView<Department> tableView;
    private final DepartmentController controller;

    public DepartmentView(DepartmentController controller) {
        this.controller = controller;
        this.tableView = new TableView<>();
        this.createTable();
        this.getChildren().addAll(new Label("Departments"), tableView);
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
}
