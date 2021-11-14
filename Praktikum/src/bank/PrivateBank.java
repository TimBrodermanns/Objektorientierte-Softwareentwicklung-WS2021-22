package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.util.*;

public class PrivateBank implements Bank{
    private String name;
    private double incomingInterest;
    private double outgoingInterest;
    private Map<String, List<Transaction>> accountsToTransactions;

    public PrivateBank(){
        this.accountsToTransactions = new HashMap<String, List<Transaction>>();
    }

    public PrivateBank(String name, double incomming, double outgoing, Map<String, List<Transaction>> accountes){
        this.name = name;
        this.incomingInterest = incomming;
        this.outgoingInterest = outgoing;
        this.accountsToTransactions = accountes;
    }
    public PrivateBank(PrivateBank p){
        this(p.name, p.incomingInterest, p.outgoingInterest,p.accountsToTransactions);
    }


    public void createAccount(String account) throws AccountAlreadyExistsException{
        if(accountsToTransactions.containsKey(account))
            throw new AccountAlreadyExistsException();
        accountsToTransactions.put(account, new ArrayList<Transaction>());
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

    public void removeTransaction(String account, Transaction transaction) throws TransactionDoesNotExistException, AccountDoesNotExistException{
        if(!accountsToTransactions.containsKey(account)) throw new AccountDoesNotExistException();
        if(accountsToTransactions.get(account).contains(transaction))
            accountsToTransactions.get(account).remove(transaction);
        else
            throw new TransactionDoesNotExistException();
    }

    public boolean containsTransaction(String account, Transaction transaction){
        if(!accountsToTransactions.containsKey(account)) return false;
        return accountsToTransactions.get(account).contains(transaction);
    }


    public double getAccountBalance(String account){
        double balance = 0.0;
        for(Transaction b : accountsToTransactions.get(account)) balance += b.calculate();
        return balance;
    }

    public List<Transaction> getTransactions(String account){
        return accountsToTransactions.get(account);
    }

    public List<Transaction> getTransactionsSorted(String account, boolean asc){
        List<Transaction> tmp = accountsToTransactions.get(account);
        tmp.sort( Comparator.comparing(Transaction::getAmount));
        if(!asc) Collections.reverse(tmp);
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

    @Override
    public String toString(){
        return this.name + " "+ this.incomingInterest + this.outgoingInterest + " " + this.accountsToTransactions.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o == null) throw new NullPointerException();
        if(o.getClass() != PrivateBank.class) throw new IllegalArgumentException();
        PrivateBank b = (PrivateBank)o;
        return this.name.equals(b.name) &&
                Double.compare(this.incomingInterest, b.incomingInterest) == 0 &&
                Double.compare(this.outgoingInterest, b.outgoingInterest) == 0 &&
                this.accountsToTransactions.equals(b.accountsToTransactions);
    }
}
