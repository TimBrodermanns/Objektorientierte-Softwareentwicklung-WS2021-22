package bank.exceptions;

public class TransferNotValidException extends IllegalArgumentException{
    public TransferNotValidException(String msg){
        super(msg);
    }
}
