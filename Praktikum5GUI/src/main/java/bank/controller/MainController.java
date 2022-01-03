package bank.controller;

import bank.Main;
import bank.PrivateBank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class MainController {

    PrivateBank pb;

    @FXML
    public void initialize(){
        pb = new PrivateBank("Main", 0.1,0.1);
        populateListView();
    }

    @FXML
    private ListView<Label> AccountList;

    @FXML
    private Label welcomeText;

    @FXML
    private Button ButtonWel;

    @FXML
    private TextField NewName;

    @FXML
    private Button createNewAccount;

    private void populateListView() {
        this.AccountList.getItems().clear();
        for (String s : pb.getAllAccounts()) {
            Label l = new Label(s);
            ContextMenu cm = new ContextMenu();
            MenuItem Show = new MenuItem("Show");
            Show.setOnAction((event) -> {
                try {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AccountDetails-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                    AccountDetailsController controller = fxmlLoader.<AccountDetailsController>getController();
                    controller.SetUsername(s);
                    controller.setPrivateBank(pb);
                    controller.init();
                    stage.setTitle("Account of: " + s);
                    stage.setScene(scene);
                    stage.show();
                    Stage currstage = (Stage) AccountList.getScene().getWindow();
                    currstage.close();

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            });
            MenuItem delete = new MenuItem("Delete");
            delete.setOnAction((Event) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Account löschen?");
                alert.setContentText("Sie sind dabei den account von \"" + s + "\" zu löschen, wollen sie wirklich Fortfahren");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    try {
                    pb.deleteAccount(s);
                    this.populateListView();
                    }catch (Exception e){}
                }
            });
            cm.getItems().addAll(Show, delete);
            l.setContextMenu(cm);
            AccountList.getItems().add(l);
        }
    }

    public void addAccount(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Account Hinzufügen?");
        alert.setContentText("Erstellen von: " + NewName.getText());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            try {
                pb.createAccount(NewName.getText());
                this.populateListView();
                this.createNewAccount.setText("Account hinzufügen");
            }
            catch (Exception e){
                System.out.println("Error at account creation");
            }
        }
    }

    public MainController(){
    }


}