package bank;

public class Transaction {
    private String date;
    private double amount;
    private String description;

    /**
     *
     *  <p>creates a Transaction object</p>
     *
     *  @param Date             Date in format DD.MM.YYYY
     *  @param Amount           Amount of Money
     *  @param Description      Description of the Transaction
     */
    public Transaction(String Date, double Amount, String Description){
        this.date = Date;
        this.amount = Amount;
        this.description = Description;
    }

    /**
     * @param t copies t into a new object
     */
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

    /**
     * <p>Prints all variables into the Console</p>
     */
    public void printObject(){
        System.out.print("Date: " + date +"\t | Amount: " + amount+ "\t | Description: " + description );
    }

}
