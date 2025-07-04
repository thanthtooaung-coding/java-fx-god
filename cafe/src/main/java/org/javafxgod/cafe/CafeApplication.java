package org.javafxgod.cafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CafeApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load the FXML file that defines the layout.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cafe.fxml"));
        Parent root = loader.load();

        // Set the title of the window.
        primaryStage.setTitle("Cafe Counter System");

        // Create the scene with the loaded layout.
        Scene scene = new Scene(root);

        // Set the scene to the stage.
        primaryStage.setScene(scene);

        // Prevent the window from being resized to maintain design integrity.
        primaryStage.setResizable(false);

        // Show the stage.
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
