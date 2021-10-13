package bank;


public class Transfer extends Transaction {

    private String sender;
    private String recipient;

    public Transfer(String Date, double Amount, String Description, String Sender, String Recipient){
        super(Date,Amount,Description);
        this.sender = Sender;
        this.recipient = Recipient;
    }

    public Transfer(Transfer t){
        this(t.getDate(), t.getAmount(),t.getDescription(),t.sender, t.recipient);
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void printObject(){
        super.printObject();
        System.out.println("\t | Sender: " + sender + "\t | Recipient" + recipient);
    }
}
