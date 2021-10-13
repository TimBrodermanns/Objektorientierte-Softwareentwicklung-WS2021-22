public class Transfer extends Transaction{

    private String sender;
    private String recipient;

    public Transfer(String Date, double Amount, String Description, String Sender, String Recipient){
        super(Date,Amount,Description);
        this.sender = Sender;
        this.recipient = Recipient;
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
}
