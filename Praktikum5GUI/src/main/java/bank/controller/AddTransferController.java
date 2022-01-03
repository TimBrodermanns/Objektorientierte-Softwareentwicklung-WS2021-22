package bank.controller;

import bank.PrivateBank;
import bank.Transfer;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.List;

public class AddTransferController {
    private PrivateBank pb;
    public void setPb(PrivateBank _pb){
        pb = _pb;
    }
    private String username;
    public void setUsername(String _username){ username = _username; }
    public void init(){
        for(String s : this.pb.getAllAccounts()){
            if(this.username == s ) continue;
            Label l = new Label(s);
            this.reci.getItems().add(l);
        }
        this.reci.setButtonCell(new ListCell(){
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
    }

    @FXML
    private TextField date;
    @FXML
    private TextField amount;
    @FXML
    private TextField des;
    @FXML
    private ComboBox reci;

    public void sendTransfer() throws Exception{
        boolean error = false;
        double parsedAmount = 0;
        if(date.getText().length() < 1) error = true;
        if(amount.getText().length() < 1) error = true;
        if(des.getText().length() < 1) error = true;
        if(reci.getValue() == null) error = true;
        else error = !this.pb.existsAccount(((Label)this.reci.getValue()).getText());
        try{
            parsedAmount = Double.parseDouble(amount.getText());
        }catch (Exception e){
            error = true;
        }

        if(error) JOptionPane.showMessageDialog(new JFrame(), "Ups, da ist was schief gelaufen", "Dialog", JOptionPane.ERROR_MESSAGE);
        else{
            this.pb.addTransaction(this.username, new Transfer(date.getText(), parsedAmount, des.getText(), this.username, ((Label)this.reci.getValue()).getText()));
            Stage currstage = (Stage) amount.getScene().getWindow();
            currstage.close();
        }
    }

}
