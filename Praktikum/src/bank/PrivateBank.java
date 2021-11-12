package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class PrivateBank implements Bank{
    private String name;
    private double incomingInterest;
    private double outgoingInterest;
    private Map<String, List<Transaction>> accountsToTransactions;

    public void createAccount(String account) throws AccountAlreadyExistsException{
        if(accountsToTransactions.containsKey(account))
            throw new AccountAlreadyExistsException();
        accountsToTransactions.put(account, null);
    }

    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException{
        if(accountsToTransactions.containsKey(account))
            throw new AccountAlreadyExistsException();

        for(Transaction t : transactions){
            if(t.getClass() == Payment.class ){
                ((Payment) t).setIncomingInterest(this.incomingInterest);
                ((Payment) t).setOutgoingInterest(this.outgoingInterest);
            }
        }
        accountsToTransactions.put(account, transactions);
    }

    public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException{
        if(!accountsToTransactions.containsKey(account))
            throw new AccountDoesNotExistException();
        if(accountsToTransactions.get(account).contains(transaction))
            throw new TransactionAlreadyExistException();

        if(transaction.getClass() == Payment.class ){
            ((Payment) transaction).setIncomingInterest(this.incomingInterest);
            ((Payment) transaction).setOutgoingInterest(this.outgoingInterest);
        }

        List<Transaction> tmp = accountsToTransactions.get(account);
        tmp.add(transaction);
        accountsToTransactions.put(account, tmp);
    }

    public void removeTransaction(String account, Transaction transaction) throws TransactionDoesNotExistException{
        if(!accountsToTransactions.containsKey(account)) return;
        if(!accountsToTransactions.get(account).contains(transaction)) throw new TransactionDoesNotExistException();
        accountsToTransactions.get(account).remove(transaction);
    }

    public boolean containsTransaction(String account, Transaction transaction){
        if(!accountsToTransactions.containsKey(account)) return false;
        return accountsToTransactions.get(account).contains(transaction);
    }


    public double getAccountBalance(String account){
        double balance = 0.0;
        for(Transaction b : accountsToTransactions.get(account)){
            balance = balance + b.getAmount();
        }
        return balance;
    }

    public List<Transaction> getTransactions(String account){
        return accountsToTransactions.get(account);
    }

    public List<Transaction> getTransactionsSorted(String account, boolean asc){
        List<Transaction> tmp = accountsToTransactions.get(account);
        tmp.sort( Comparator.comparing(Transaction::getAmount));
        return tmp;
    }

    public List<Transaction> getTransactionsByType(String account, boolean positive){
        ArrayList<Transaction> t = new ArrayList<Transaction>();
        for(Transaction tmp : accountsToTransactions.get(account)){
            if(tmp.getAmount() > 0 && positive) t.add(tmp);
            if(tmp.getAmount() <= 0 && !positive) t.add(tmp);
        }
        return t;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }


    public String getName() {
        return name;
    }

    public double getIncomingInterest() {
        return incomingInterest;
    }

    public double getOutgoingInterest() {
        return outgoingInterest;
    }
}
