
import bank.*;

public class Main {
    public static void main(String[] args){

        Payment p1 = new Payment("13.10.2021", 1000, "Test Incomming", 0.05, 0.1);
        Payment p2 = new Payment("13.10.2021", -1000, "Test Incomming", 0.05, 0.1);

        Transfer t1 = new Transfer("13.10.2021", 1000, "Eine andere Beschreibung", "Der Sender", "Der Empfänger");
        Transfer t2 = new Transfer("13.10.2021", -1000, "Eine andere Beschreibung", "Der Sender", "Der Empfänger");

        p1.calculate();
        p2.calculate();
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(t1.toString());
        System.out.println(t2.toString());
    }


}
