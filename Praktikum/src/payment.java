public class payment extends transaction {

    private double incomingInterest;
    private double outgoingInterest;

    public payment(String Date, double Amount, String Description, double IncomingInterest, double OutgoingInterest){
        super(Date,Amount,Description);
        this.incomingInterest = IncomingInterest;
        this.outgoingInterest = OutgoingInterest;
    }

    public double getIncomingInterest() {
        return incomingInterest;
    }

    public void setIncomingInterest(double incomingInterest) {
        this.incomingInterest = incomingInterest;
    }

    public double getOutgoingInterest() {
        return outgoingInterest;
    }

    public void setOutgoingInterest(double outgoingInterest) {
        this.outgoingInterest = outgoingInterest;
    }
}
