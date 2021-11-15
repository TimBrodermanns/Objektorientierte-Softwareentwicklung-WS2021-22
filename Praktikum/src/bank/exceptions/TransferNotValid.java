package bank.exceptions;

public class TransferNotValid extends IllegalArgumentException{
    public TransferNotValid(String msg){
        super(msg);
    }
}
