module com.example.osago {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.junit.jupiter.api;


    opens com.example.osago to javafx.fxml;
    exports com.example.osago;
}