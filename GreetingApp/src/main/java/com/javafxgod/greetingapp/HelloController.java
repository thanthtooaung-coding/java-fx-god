package com.javafxgod.greetingapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Clicking....");
        welcomeText.setText("Welcome to JavaFX Application!");
        welcomeText.setStyle("-fx-font-weight: bold");
    }
}