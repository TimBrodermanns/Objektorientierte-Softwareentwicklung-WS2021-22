import bank.PrivateBank;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import org.junit.jupiter.api.*;
import bank.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class TestPrivateBank {

    @Test
    public void TestEqual(){

        HashMap<String, List<Transaction>> m = new HashMap<String, List<Transaction>>();
        ArrayList<Transaction> l = new ArrayList<>();
        l.add(new Payment("", 1,"",1,1));
        l.add(new Payment("", 1,"",2,1));

        PrivateBank p = new PrivateBank("Sparkasse", 0.1,0.1,m);
        PrivateBank p1 = new PrivateBank("Sparkasse", 0.1,0.1,m);
        PrivateBank p2 = new PrivateBank("Sparkasse1", 0.1,0.1,m);
        System.out.print(p.equals(p1));
        assertTrue(p.equals(p));
        assertFalse(p.equals(p2));
    }
    @Test
    public void TestCopyConstructor(){
        HashMap<String, List<Transaction>> m = new HashMap<String, List<Transaction>>();
        PrivateBank p = new PrivateBank("Sparkasse", 0.1,0.1,m);
        PrivateBank pb = new PrivateBank(p);
        assertTrue(p.equals(pb));
    }

    @Test
    public void TestAccountCreation(){
        PrivateBank pb = new PrivateBank();
        pb.createAccount("User");
        pb.createAccount("User1");

        Exception exception = assertThrows(AccountAlreadyExistsException.class, () -> {
            pb.createAccount("User1");
        });
    }
    @Test
    public void TestAddTransaction(){
        PrivateBank pb = new PrivateBank();
        pb.createAccount("User1");
        pb.addTransaction("User1", new Payment("", 1,"",1,1));
        Exception exception = assertThrows(AccountDoesNotExistException.class, () -> {
            pb.addTransaction("User", new Payment("", 1,"",1,1));
        });
        Exception exception1 = assertThrows(TransactionAlreadyExistException.class, () -> {
            pb.addTransaction("User1", new Payment("", 1,"",1,1));
        });
    }
}
