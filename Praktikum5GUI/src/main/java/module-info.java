module bank.praktikum5gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;


    opens bank to javafx.fxml;
    exports bank;
    exports bank.controller;
    opens bank.controller to javafx.fxml;
}