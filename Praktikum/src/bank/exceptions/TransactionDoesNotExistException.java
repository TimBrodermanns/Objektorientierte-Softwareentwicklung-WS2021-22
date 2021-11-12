package bank.exceptions;

public class TransactionDoesNotExistException extends IllegalArgumentException{
    public TransactionDoesNotExistException(){
        super("Transaction Does Not Exist");
    }
}
