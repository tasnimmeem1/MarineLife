module org.example.marinelife {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.marinelife to javafx.fxml;
    exports org.example.marinelife;
}