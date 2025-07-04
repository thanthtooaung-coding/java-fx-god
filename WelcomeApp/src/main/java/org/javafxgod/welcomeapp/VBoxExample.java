package org.javafxgod.welcomeapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label1 = new Label("Welcome to JavaFX");
        Label label2 = new Label("This is VBox Layout");

        VBox vbox = new VBox(10); // 10px spacing
        vbox.getChildren().addAll(label1, label2);

        Scene scene = new Scene(vbox, 300, 150);
        primaryStage.setTitle("VBox Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
