module org.javafxgod.cafe {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.javafxgod.cafe to javafx.fxml;
    exports org.javafxgod.cafe;
}