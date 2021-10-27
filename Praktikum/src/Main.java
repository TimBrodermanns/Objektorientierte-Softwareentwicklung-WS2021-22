import bank.*;

public class Main {
    public static void main(String[] args){

        Payment p1 = new Payment("13.10.2021", 1000, "Eine Beschreibung", 0.05, 0.1);
        Payment p2 = new Payment(p1);
        p2.setAmount(-1000);
        Transfer t1 = new Transfer("13.10.2021", 1000, "Eine andere Beschreibung", "Der Sender", "Der Empfänger");
        Transfer t2 = new Transfer(t1);
        p1.setDescription("Eine geänderte Beschreibung");
        t1.setDescription("Diese Beschreibung ist auch geändert");

        System.out.println("incomingInterest Test: " + p1.calculate());
        System.out.println("outgoingInterest Test: " + p2.calculate());

        System.out.println(p1.toString());
        System.out.println(p2.toString());

    }
}
