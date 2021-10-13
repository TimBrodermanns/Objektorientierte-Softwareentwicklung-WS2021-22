package bank;

public class Payment extends Transaction {

    private double incomingInterest;
    private double outgoingInterest;

    public Payment(String Date, double Amount, String Description, double IncomingInterest, double OutgoingInterest){
        super(Date,Amount,Description);
        this.incomingInterest = IncomingInterest;
        this.outgoingInterest = OutgoingInterest;
    }

    public Payment(Payment p){
        this(p.getDate(), p.getAmount(), p.getDescription(), p.incomingInterest, p.outgoingInterest);
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

    public void printObject(){
        super.printObject();
        System.out.println("\t | IncomingInterest: " + incomingInterest + "\t | OutgoingInterest: " + outgoingInterest);
    }
}
