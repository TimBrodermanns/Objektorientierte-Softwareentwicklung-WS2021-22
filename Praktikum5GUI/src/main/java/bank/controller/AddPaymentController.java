package bank.controller;

import bank.Payment;
import bank.PrivateBank;
import bank.Transfer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class AddPaymentController {
    private PrivateBank pb;
    public void setPb(PrivateBank _pb){ pb = _pb; }
    private String username;
    public void setUsername(String _username){ username = _username; }

    @FXML
    public TextField DateTF;

    @FXML
    public TextField amountTF;

    @FXML
    public TextField desTF;

    public void sendPayment() throws Exception{
        boolean error = false;
        double parsedAmount = 0.0;
        if(DateTF.getText().length() < 1) error = true;
        if(amountTF.getText().length() < 1) error = true;
        if(desTF.getText().length() < 1) error = true;
        try{
             parsedAmount = Double.parseDouble(amountTF.getText());
        }catch (Exception e){
            error = true;
        }
        if(error)
            JOptionPane.showMessageDialog(new JFrame(), "Ups, da ist was schief gelaufen", "Dialog", JOptionPane.ERROR_MESSAGE);
        else{
            pb.addTransaction(this.username, new Payment(desTF.getText(), parsedAmount, desTF.getText(),0,0));
            Stage currstage = (Stage) amountTF.getScene().getWindow();
            currstage.close();
        }
    }


}
