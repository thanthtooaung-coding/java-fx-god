package org.javafxgod.welcomeapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginWithSceneSwitch extends Application {

    private Scene loginScene;
    private Scene welcomeScene;

    @Override
    public void start(Stage stage) {
        // ===== LOGIN SCENE =====
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");
        Label statusLabel = new Label();

        VBox loginLayout = new VBox(10);
        loginLayout.getChildren().addAll(
            new HBox(10, userLabel, userField),
            new HBox(10, passLabel, passField),
            loginButton,
            statusLabel
        );
        loginScene = new Scene(loginLayout, 350, 200);

        // ===== WELCOME SCENE =====
        Label welcomeLabel = new Label("🎉 Welcome to the Dashboard!");
        Button logoutButton = new Button("Logout");

        VBox welcomeLayout = new VBox(20, welcomeLabel, logoutButton);
        welcomeScene = new Scene(welcomeLayout, 350, 200);

        // ===== LOGIN LOGIC =====
        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            if (username.equals("alvin") && password.equals("password123!")) {
                statusLabel.setText("");
                stage.setScene(welcomeScene); // 👈 Scene Switch
            } else {
                statusLabel.setText("❌ Wrong username or password");
            }
        });

        // ===== LOGOUT LOGIC =====
        logoutButton.setOnAction(e -> {
            userField.clear();
            passField.clear();
            stage.setScene(loginScene); // 👈 Back to Login
        });

        // ===== INITIAL SCENE =====
        stage.setTitle("JavaFX Scene Switching");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
