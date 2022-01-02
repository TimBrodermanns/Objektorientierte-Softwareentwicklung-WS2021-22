package bank;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class AccountDetailsController {

    private String Username;
    private PrivateBank pb;
    private Stage stage;
    public void setStage(Stage s){stage = s;}
    public void setPrivateBank(PrivateBank p){
        pb = p;
    }
    public void SetUsername(String u){
        Username = u;
    }

    @FXML
    public ComboBox SortingOptions;

    @FXML
    public ListView transactionView;

    @FXML
    private Label yourBalance;

    private void printBalance(){
        yourBalance.setText("Balance: " + pb.getAccountBalance(Username));
    }

    private void populateTransactionViewWithTransactionList(List<Transaction> tl){
        this.transactionView.getItems().clear();
        try {
            for (Transaction t : tl) {
                String s = new String();
                if (t instanceof Payment) s = ((Payment) t).toString();
                else if (t instanceof IncomingTransfer) s = ((IncomingTransfer) t).toString();
                else if (t instanceof OutgoingTransfer) s = ((OutgoingTransfer) t).toString();
                else continue;
                ContextMenu cm = new ContextMenu();
                MenuItem delete = new MenuItem("Delete");
                delete.setOnAction((Event) ->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Transaction Löschen");
                    alert.setContentText("Durch das Löschen dieser Transaction wird sie unwiederruflich gelöscht");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        System.out.println("Transaction delited");
                        try {
                            pb.removeTransaction(Username, t);
                        }catch (Exception e){}
                    }
                });
                cm.getItems().add(delete);
                Label l = new Label(s);
                l.setContextMenu(cm);
                transactionView.getItems().add(l);
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    public void refresh(){
        printBalance();
        populateTransactionViewWithTransactionList(this.pb.getTransactions(this.Username));
    }

    public void init(){
        refresh();
        Label ascSorting = new Label("Aufsteigende Sortierung");
        Label descSorting = new Label("Absteigende Sortierung");
        Label postiveValues = new Label("Anzeige von nur positiven amount");
        Label neagiveValues = new Label("Anzeige von nur negativen amounts");
        // without this SortingOptions changes color after first time selected
        SortingOptions.setButtonCell(new ListCell(){
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if(empty || item==null){
                    // styled like -fx-prompt-text-fill:
                    setStyle("-fx-text-fill: derive(-fx-control-inner-background,-30%)");
                } else {
                    setStyle("-fx-text-fill: -fx-text-inner-color");
                    setText(((Label)item).getText());
                }
            }
        });
        SortingOptions.getItems().addAll(ascSorting,descSorting,postiveValues,neagiveValues);
        SortingOptions.setOnAction((Event)->{
            if(SortingOptions.getValue() == ascSorting) populateTransactionViewWithTransactionList(this.pb.getTransactionsSorted(this.Username, true));
            if(SortingOptions.getValue() == descSorting) populateTransactionViewWithTransactionList(this.pb.getTransactionsSorted(this.Username, false));
            if(SortingOptions.getValue() == postiveValues) populateTransactionViewWithTransactionList(this.pb.getTransactionsByType(this.Username, true));
            if(SortingOptions.getValue() == neagiveValues) populateTransactionViewWithTransactionList(this.pb.getTransactionsByType(this.Username, false));
        });
    }

    @FXML
    public void back() throws  Exception{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("My Bank");
        stage.setScene(scene);
        stage.show();
        Stage currstage = (Stage) SortingOptions.getScene().getWindow();
        currstage.close();
    }
}
