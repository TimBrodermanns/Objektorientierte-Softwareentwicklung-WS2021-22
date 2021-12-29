package bank.exceptions;

public class AccountDoesNotExistException extends IllegalArgumentException{
    public AccountDoesNotExistException(){
        super("Account Does Not Exist");
    }
    public AccountDoesNotExistException(String msg){super(msg); }
}
