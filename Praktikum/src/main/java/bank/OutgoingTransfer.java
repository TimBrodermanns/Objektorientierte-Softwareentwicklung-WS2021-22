package bank;

public class OutgoingTransfer extends Transfer {
    public OutgoingTransfer(String Date, double Amount, String Description, String Sender, String Recipient){
        super(Date, Amount, Description, Sender, Recipient);
    }

    public OutgoingTransfer(Transfer og){
        super(og.getDate(), og.getAmount(), og.getDescription(), og.getSender(),og.getRecipient());
    }

    public OutgoingTransfer(OutgoingTransfer og){
        super(og.getDate(), og.getAmount(), og.getDescription(), og.getSender(),og.getRecipient());
    }

    @Override
    public double calculate(){
        return this.getAmount() * -1;
    }
}
