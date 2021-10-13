package bank;

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
    *   @param p creates a copy of p
    */
    public Payment(Payment p){
        this(p.getDate(), p.getAmount(), p.getDescription(), p.incomingInterest, p.outgoingInterest);
    }

    /**
     * @return returns incoming interest of payment
     */
    public double getIncomingInterest() {
        return incomingInterest;
    }

    /**
     * <p>Sets new incoming interest</p>
      * @param incomingInterest new interest
     */
    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    /**
     * @return returns outgoing interest of payment
     */
    public double getOutgoingInterest() {
        return outgoingInterest;
    }

    /**
     * <p>sets new outgoing interest</p>
     * @param outgoingInterest new interest
     */
    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }


    /**
     * <p>Prints all variables into the Console</p>
     */
    public void printObject(){
        super.printObject();
        System.out.println("\t | IncomingInterest: " + incomingInterest + "\t | OutgoingInterest: " + outgoingInterest);
    }
}
