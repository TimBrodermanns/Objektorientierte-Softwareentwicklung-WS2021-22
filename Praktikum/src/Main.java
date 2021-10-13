import bank.*;

public class Main {
    public static void main(String[] args){

        Payment p1 = new Payment("13.10.2021", 100, "Eine Beschreibung", 0.1, 0.1);
        Payment p2 = new Payment(p1);
        Transfer t1 = new Transfer("13.10.2021", 100, "Eine andere Beschreibung", "Der Sender", "Der Empfänger");
        Transfer t2 = new Transfer(t1);
        p1.setDescription("Eine geänderte Beschreibung");
        t1.setDescription("Diese Beschreibung ist auch geändert");

        p1.printObject();
        p2.printObject();
        t1.printObject();
        t2.printObject();

    }
}
