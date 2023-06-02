module com.example.sixquiprend {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.jdeps;


    opens com.example.sixquiprend to javafx.fxml;
    exports com.example.sixquiprend;
    exports com.example.sixquiprend.Vue.Interface;
    opens com.example.sixquiprend.Vue.Interface to javafx.fxml;
}