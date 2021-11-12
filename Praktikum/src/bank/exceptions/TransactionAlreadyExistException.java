package bank.exceptions;

public class TransactionAlreadyExistException extends IllegalArgumentException{
    public TransactionAlreadyExistException(){
        super("Transaction Already Exist");
    }
}
