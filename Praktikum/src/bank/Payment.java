package bank;

/**
 * <p>Handels deposits and withdrawals</p>
 * @see bank.Transaction
 */
public class Payment extends Transaction {

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
    public void printObject(){
        super.printObject();
        System.out.println("\nIncom-Interest: " + incomingInterest + "\nOutgo-Interest: " + outgoingInterest);
    }
}
