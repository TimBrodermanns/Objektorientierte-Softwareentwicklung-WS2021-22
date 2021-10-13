package bank;


public class Transfer extends Transaction {

    private String sender;
    private String recipient;

    /**
     *
     *  <p>creates a Transfer object</p>
     *
     *  @param Date             Date in format DD.MM.YYYY
     *  @param Amount           Amount of Money
     *  @param Description      Description of the Transaction
     *  @param Sender           Person that sends the transaction
     *  @param Recipient        Person that recives the transaction
     */
    public Transfer(String Date, double Amount, String Description, String Sender, String Recipient){
        super(Date,Amount,Description);
        this.sender = Sender;
        this.recipient = Recipient;
    }

    /**
     * @param t copies t into a new object
     */
    public Transfer(Transfer t){
        this(t.getDate(), t.getAmount(),t.getDescription(),t.sender, t.recipient);
    }

    /**
     *
     * @return returns the sender of Transfer
     */
    public String getSender() {
        return sender;
    }

    /**
     * <p>Sets new sender of transfer</p>
     * @param sender new sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     *
     * @return returns recipient of transaction
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * <p>Sests new recipient of transfer</p>
     * @param recipient new recipient
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * <p>Prints all variables into the Console</p>
     */
    public void printObject(){
        super.printObject();
        System.out.println("\t | Sender: " + sender + "\t | Recipient" + recipient);
    }
}
