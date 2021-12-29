module bank.praktikum5gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens bank to javafx.fxml;
    exports bank;
}