package bank.exceptions;

public class AccountAlreadyExistsException extends IllegalArgumentException {
    public AccountAlreadyExistsException(){
        super("Account Already Exists");
    }
}
