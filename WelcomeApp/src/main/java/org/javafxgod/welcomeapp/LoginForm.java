package org.javafxgod.welcomeapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginForm extends Application {
    @Override
    public void start(Stage stage) {
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");
        Label statusLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            if (username.equals("alvin") && password.equals("password123!")) {
                statusLabel.setText("Logged in as: " + username);
            } else {
                statusLabel.setText("❌ Wrong username or password");
            }
        });

        VBox form = new VBox(10);
        form.getChildren().addAll(
                new HBox(10, userLabel, userField),
                new HBox(10, passLabel, passField),
                loginButton,
                statusLabel
        );

        Scene scene = new Scene(form, 350, 200);
        stage.setTitle("Login Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
