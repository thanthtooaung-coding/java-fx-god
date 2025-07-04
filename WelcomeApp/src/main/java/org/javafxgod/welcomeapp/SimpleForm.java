package org.javafxgod.welcomeapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleForm extends Application {
    @Override
    public void start(Stage stage) {
        TextField nameInput = new TextField();
        Button button = new Button("Say Hello");
        Label result = new Label();

        button.setOnAction(e -> {
            String name = nameInput.getText();
            result.setText("Hello, " + name + "!");
        });

        VBox vbox = new VBox(10, nameInput, button, result);
        Scene scene = new Scene(vbox, 300, 150);
        stage.setTitle("Simple Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
