module com.example.java1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.java1 to javafx.fxml;
    exports com.example.java1;
}