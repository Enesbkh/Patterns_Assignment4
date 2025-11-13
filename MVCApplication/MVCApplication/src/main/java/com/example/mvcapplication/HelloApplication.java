package com.example.mvcapplication;

import com.example.mvcapplication.controllers.DepartmentController;
import com.example.mvcapplication.controllers.EmployeeController;
import com.example.mvcapplication.controllers.ProjectController;
import com.example.mvcapplication.views.DepartmentView;
import com.example.mvcapplication.views.EmployeeView;
import com.example.mvcapplication.views.ProjectView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {


//        EmployeeController controller = new EmployeeController();
//        EmployeeView view = new EmployeeView(controller);
//
//        Scene scene = new Scene(view, 400, 300);
//        stage.setTitle("Employee Table (MVC)");
//        stage.setScene(scene);
//        stage.show();


        //Testing of the Department window
//        DepartmentController deptController = new DepartmentController();
//        DepartmentView deptView = new DepartmentView(deptController);
//
//        Scene deptScene = new Scene(deptView, 400, 300);
//        stage.setTitle("Departments (MVC)");
//        stage.setScene(deptScene);
//        stage.show();

        //test project window
        ProjectController projectController = new ProjectController();
        ProjectView projectView = new ProjectView(projectController);

        Scene projScene = new Scene(projectView,400, 300);
        stage.setTitle("Projects (mvc)");
        stage.setScene(projScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}