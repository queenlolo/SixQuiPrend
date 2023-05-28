module com.example.sixquiprend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.sixquiprend to javafx.fxml;
    exports com.example.sixquiprend;
}