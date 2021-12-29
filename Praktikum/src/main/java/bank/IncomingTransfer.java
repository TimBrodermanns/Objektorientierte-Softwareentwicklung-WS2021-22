package bank;

public class IncomingTransfer extends Transfer {
    public IncomingTransfer(String Date, double Amount, String Description, String Sender, String Recipient){
        super(Date, Amount, Description, Sender, Recipient);
    }

    public IncomingTransfer(Transfer ip){
        super(ip.getDate(), ip.getAmount(), ip.getDescription(), ip.getSender(), ip.getRecipient());
    }

    public IncomingTransfer(IncomingTransfer ip){
        super(ip.getDate(), ip.getAmount(), ip.getDescription(), ip.getSender(), ip.getRecipient());
    }

    @Override
    public double calculate(){
        return this.getAmount();
    }
}
