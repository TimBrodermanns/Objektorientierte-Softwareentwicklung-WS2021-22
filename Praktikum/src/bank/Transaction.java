package bank;


/**
 * <p>Super Class for all Transaction</p>
 */
public abstract class Transaction implements CalculateBill {
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

    /**
     * <p>returns date of that Transaction</p>
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * <p>sets the date of the transaction</p> </br>
     * <p>Format DD.MM.YYYY</p>
     * @param date new date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * <p>Amount of the transactions</p>
     * @return Amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * <p>sets the new Amount of transaction</p>
     * @param amount new Amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * <p>returns the description of the transaction</p>
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>Set new Description to the transaction</p>
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>Prints all variables into the Console</p>
     */
    public String toString(){
        return "\nDate: \t\t\t" + date +"\nAmount: \t\t" + amount+ "\nDescription: \t" + description;
    }


    /**
     * <p>Checks if to Objects are Equal</p>
     * @param obj Transfer object to check
     * @return true if its equal
     */

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final Transaction other = (Transaction) obj;
        return this.description.equals(other.description)  && Double.compare(this.amount, other.amount) == 0 && this.date.equals(other.date);
    }
}
