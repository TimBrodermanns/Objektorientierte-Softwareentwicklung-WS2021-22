package bank;

/**
 * <p>Handels deposits and withdrawals</p>
 * @see bank.Transaction
 */
public class Payment extends Transaction{

    private double incomingInterest;
    private double outgoingInterest;

    /**
     *
     *  <p>creates a payment object</p>
     *
     *  @param Date             Date in format DD.MM.YYYY
     *  @param Amount           Amount of Money
     *  @param Description      Description of the Transaction
     *  @param IncomingInterest Incoming interest of that transaction, Value in %, 0 to 1
     *  @param OutgoingInterest Outgoing interest of that transaction, Value in %, 0 to 1
     */
    public Payment(String Date, double Amount, String Description, double IncomingInterest, double OutgoingInterest){
        super(Date,Amount,Description);
        this.incomingInterest = IncomingInterest;
        this.outgoingInterest = OutgoingInterest;
        this.calculate();
    }

    /**
     * <p>creates an identical payment object of p</p>
    *   @param p creates a copy of p
    */
    public Payment(Payment p){
        this(p.getDate(), p.getAmount(), p.getDescription(), p.incomingInterest, p.outgoingInterest);
    }

    /**
     * <p>returns incoming interest of payment</p>
     * @return incoming interest
     */
    public double getIncomingInterest() {
        return incomingInterest;
    }

    /**
     * <p>Sets new incoming interest</p>
     *  <p>IncomingInterest Incoming interest of that transaction, Value in %, 0 to 1</p>
     * @param incomingInterest new interest
     */
    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    /**
     * <p>returns outgoing interest of payment</p>
     * @return outgoing interest
     */
    public double getOutgoingInterest() {
        return outgoingInterest;
    }

    /**
     * <p>sets new outgoing interest</p>
     * <p>Outgoing interest of that transaction, Value in %, 0 to 1</p>
     * @param outgoingInterest new interest
     */
    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }

    /**
     * <p>Prints all variables into the Console</p>
     */
    @Override
    public String toString(){
        String ret = super.toString() + ", Incom-Interest: " + incomingInterest + ", Outgo-Interest: " + outgoingInterest;
        return ret;
    }

    /**
     *  <p>Calculates the new amount of your balance</p>
     * @return new balance
     */
    public double calculate(){
        return (this.getAmount() > 0) ? (this.getAmount() - this.getAmount()*incomingInterest) : (this.getAmount() + this.getAmount()*this.outgoingInterest);
    }

    /**
     * <p>Checks if to Objects are Equal</p>
     * @param obj Payment object to check
     * @return true if its equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        final Payment other = (Payment) obj;
        return super.equals(other) && (Double.compare(this.outgoingInterest, other.outgoingInterest) == 0  && Double.compare(other.incomingInterest, other.incomingInterest)== 0);
    }
}