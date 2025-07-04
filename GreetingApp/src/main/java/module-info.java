module com.javafxgod.greetingapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javafxgod.greetingapp to javafx.fxml;
    exports com.javafxgod.greetingapp;
}