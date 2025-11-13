package com.example.mvcapplication.views;

import com.example.mvcapplication.controllers.EmployeeController;
import com.example.mvcapplication.controllers.ProjectController;
import com.example.mvcapplication.models.Employee;
import com.example.mvcapplication.models.Project;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class ProjectView extends VBox {
    private final TableView<Project> tableView;
    private final ProjectController controller;

    public ProjectView(ProjectController controller) throws SQLException {
        this.tableView = new TableView<>();
        this.controller = controller;
        this.createTable();
        this.getChildren().addAll(createSearchBoxProject(),tableView);
        this.bindTableData();
        this.createSearchBoxProject();
    }

    private void createTable(){
        TableColumn<Project, Integer> projectIdCol = new TableColumn<>("Project ID");
        projectIdCol.setCellValueFactory(new PropertyValueFactory<>("ProjectID"));

        TableColumn<Project, String> descriptonCol = new TableColumn<>("Project Descripton");
        descriptonCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().addAll(projectIdCol, descriptonCol);
    }

    private void bindTableData() throws SQLException {
        tableView.setItems(controller.getProjects());
    }

    private HBox createSearchBoxProject(){
        Label searchLabel = new Label("Project id");
        TextField searchTextField = new TextField();
        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            String searched = searchTextField.getText();
            tableView.setItems(controller.searchProject(searched));
        });

        HBox searchBox = new HBox(10,searchLabel,searchTextField,searchButton);
        return searchBox;


    }
}
