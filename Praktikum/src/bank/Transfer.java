package bank;

/**
 * <p>Handels transfers inbetween accounts</p>
 * @see bank.Transaction
 */
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
     * <p>Creates an identical new transfer object</p>
     * @param t copies t into a new object
     */
    public Transfer(Transfer t){
        this(t.getDate(), t.getAmount(),t.getDescription(),t.sender, t.recipient);
    }

    /**
     * <p>returns the sender of Transfer</p>
     * @return sender
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
     *<p>returns recipient of transaction</p>
     * @return recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * <p>Sets new recipient of transfer</p>
     * @param recipient new recipient
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * <p>Prints all variables into the Console</p>
     */
    @Override
    public String toString(){
        String ret = super.toString() + "\nSender: \t\t" + sender + "\nRecipient: \t\t" + recipient;
        return ret;
    }

    /**
     *  <p>Calculates the new ammount of your balance</p>
     * @return new balance
     */
    public double calculate(){
        return this.getAmount();
    }

    /**
     * <p>Checks if to Objects are Equal</p>
     * @param T Transfer object to check
     * @return true if its equal
     */
    public boolean equals(Transfer T) {
        return super.equals(T) && (this.sender == T.sender && this.recipient == T.recipient);
    }
}