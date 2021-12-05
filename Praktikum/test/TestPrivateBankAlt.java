import bank.PrivateBank;
import bank.exceptions.*;
import org.junit.jupiter.api.*;
import bank.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestPrivateBankAlt {

    @Test
    public void GetAmount(){
        PrivateBank pb = new PrivateBank();
        pb.setIncomingInterest(0.05);
        pb.setOutgoingInterest(0.01);
        pb.createAccount("User");
        try{
        pb.addTransaction("User", new Payment("One", 1000,"",0.1,0.1));
        assertTrue(pb.getAccountBalance("User") == 950);
        pb.addTransaction("User", new Payment("Two", -50,"",0.1,0.1));
        assertTrue(pb.getAccountBalance("User") == 899.5);

        pb.createAccount("User1");
        pb.addTransaction("User1", new Payment("Three", 1000,"",0.1,0.1));
        assertTrue(pb.getAccountBalance("User1") == 950);

        pb.addTransaction("User1", new Transfer("Four", 100,"","User1","User"));
        assertTrue(pb.getAccountBalance("User1") == 850);
        assertTrue(pb.getAccountBalance("User") == 999.5);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
