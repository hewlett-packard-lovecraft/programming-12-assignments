module com.example.javafxprojectassignment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.javafxprojectassignment to javafx.fxml;
    exports com.example.javafxprojectassignment;
    exports com.example.javafxprojectassignment.Model;
    opens com.example.javafxprojectassignment.Model to javafx.fxml;
}