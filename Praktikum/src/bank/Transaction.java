package bank;

public class Transaction {
    private String date;
    private double amount;
    private String description;

    public Transaction(String Date, double Amount, String Description){
        this.date = Date;
        this.amount = Amount;
        this.description = Description;
    }

    public Transaction(Transaction t){
        this(t.date, t.amount, t.description);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printObject(){
        System.out.print("Date: " + date +"\t | Amount: " + amount+ "\t | Description: " + description );
    }

}
