package bank;

import bank.exceptions.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class PrivateBank implements Bank{
    private String name = "PLACEHOLDER";
    private double incomingInterest = 0.0;
    private double outgoingInterest = 0.0;
    private Map<String, List<Transaction>> accountsToTransactions;
    private String  directoryName = name +"-Accounts";


    /***
     * std constructor for PrivateBank
     */
    public PrivateBank(){
        this.accountsToTransactions = new HashMap<String, List<Transaction>>();
    }

    /**
     * Constructor with parameters
     * @param name      Sets name of Bank
     * @param incomming Sets incoming Interest
     * @param outgoing  Sets outgoing Interest
     */
    public PrivateBank(String name, double incomming, double outgoing){
        this.name = name;
        this.incomingInterest = incomming;
        this.outgoingInterest = outgoing;
        this.accountsToTransactions = new HashMap<String, List<Transaction>>();
    }


    /**
     * Copy constructor
     * @param p object to copy
     */
    public PrivateBank(PrivateBank p){
        this(p.name, p.incomingInterest, p.outgoingInterest);
    }

    public void readAccounts() throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(RuntimeClassNameTypeAdapterFactory.of(Object.class)).create();

        File f = new File(this.directoryName);

        System.out.println("Starting reading Array");
        File[] ary = f.listFiles();
        for(int i = 0; i < ary.length; i++){
            System.out.println(ary[i].getAbsolutePath());
        }

        Arrays.stream(f.listFiles()).toList().forEach((fi)->{
            try {
                FileReader fr = new FileReader(fi.getAbsolutePath());

                Type listType = new TypeToken<List<Transaction>>(){}.getType();
                // In this test code i just shove the JSON here as string.
                List<Transaction> tr = gson.fromJson(fr, listType);

            }catch (FileNotFoundException e){
                System.out.println("Error in PrivateBank::readAccounts -> " +e.getMessage());
            }

        });

    }

    public void writeAccount(String Account) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            File f = new File(directoryName + "\\" + Account + ".json");
            f.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(f.getAbsolutePath());
            gson.toJson(accountsToTransactions.get(Account), writer);
            writer.close();
        }catch (Exception e){
            throw e;
        }
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

        if(transaction instanceof Payment){
            ((Payment) transaction).setIncomingInterest(this.incomingInterest);
            ((Payment) transaction).setOutgoingInterest(this.outgoingInterest);
            List<Transaction> tmp = accountsToTransactions.get(account);
            tmp.add(transaction);
            accountsToTransactions.put(account, tmp);
        }

        if(transaction instanceof Transfer){
            if(!accountsToTransactions.containsKey(((Transfer) transaction).getRecipient())) throw new AccountDoesNotExistException("Recipient does not Exist");
            if(!accountsToTransactions.containsKey(((Transfer) transaction).getSender())) throw new AccountDoesNotExistException("Sender does not Exist");
            if(((Transfer) transaction).getSender() != account) throw new TransferNotValidException("You can only make Transfers for your own account");

            IncomingTransfer ic = new IncomingTransfer((Transfer)transaction);
            OutgoingTransfer oc = new OutgoingTransfer((Transfer)transaction);

            // Override List at Senders Account
            List<Transaction> newSenderList = accountsToTransactions.get(((Transfer) transaction).getSender());
            newSenderList.add(oc);
            accountsToTransactions.put(((Transfer) transaction).getSender(), newSenderList);

            // Override List at Recipients account
            List<Transaction> newRecipientList = accountsToTransactions.get(((Transfer) transaction).getRecipient());
            newRecipientList.add(ic);
            accountsToTransactions.put(((Transfer) transaction).getRecipient(), newRecipientList);
        }
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
        return this.name + "\n"+ this.incomingInterest + "\n" + this.outgoingInterest + "\n" + this.accountsToTransactions.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o == null) throw new NullPointerException();
        if(!(o instanceof PrivateBank)) throw new IllegalArgumentException();
        PrivateBank b = (PrivateBank)o;
        return  this.name.equals(b.name) &&
                Double.compare(this.incomingInterest, b.incomingInterest) == 0 &&
                Double.compare(this.outgoingInterest, b.outgoingInterest) == 0 &&
                this.accountsToTransactions.equals(b.accountsToTransactions);
    }
}
