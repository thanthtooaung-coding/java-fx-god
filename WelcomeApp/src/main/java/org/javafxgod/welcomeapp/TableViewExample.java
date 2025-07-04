package org.javafxgod.welcomeapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewExample extends Application {

    public static class Student {
        private final String name;
        private final int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }

    @Override
    public void start(Stage stage) {
        TableView<Student> table = new TableView<>();

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.getColumns().addAll(nameCol, ageCol);

        ObservableList<Student> students = FXCollections.observableArrayList(
            new Student("Alvin", 20),
            new Student("Htoo", 22)
        );

        table.setItems(students);

        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox, 300, 250);
        stage.setTitle("Haircut Table");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
