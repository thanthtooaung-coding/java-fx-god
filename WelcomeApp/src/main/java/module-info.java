module org.javafxgod.welcomeapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.javafxgod.welcomeapp to javafx.fxml;
    exports org.javafxgod.welcomeapp;
}