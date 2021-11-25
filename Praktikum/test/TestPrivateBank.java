import bank.PrivateBank;
import bank.exceptions.*;
import org.junit.jupiter.api.*;
import bank.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestPrivateBank {

    @Test
    public void EqualTest(){
        PrivateBank p = new PrivateBank("Sparkasse", 0.1,0.1);
        PrivateBank p1 = new PrivateBank("Sparkasse", 0.1,0.1);
        PrivateBank p2 = new PrivateBank("Sparkasse1", 0.1,0.1);
        assertTrue(p.equals(p));
        assertFalse(p.equals(p2));
    }
    @Test
    public void CopyConstructorTest(){
        PrivateBank p = new PrivateBank("Sparkasse", 0.1,0.1);
        PrivateBank pb = new PrivateBank(p);
        assertTrue(p.equals(pb));
    }

    @Test
    public void AccountCreationTest(){
        PrivateBank pb = new PrivateBank();
        pb.createAccount("User");
        pb.createAccount("User1");

        Exception exception = assertThrows(AccountAlreadyExistsException.class, () -> {
            pb.createAccount("User1");
        });
    }
    @Test
    public void AddTransactionTest(){
        PrivateBank pb = new PrivateBank();
        Payment p = new Payment("twice", 1,"",1,1);
        pb.createAccount("User");
        pb.addTransaction("User", p);
        Exception exception = assertThrows(AccountDoesNotExistException.class, () -> {
            pb.addTransaction("User1", p);
        });
        Exception exception1 = assertThrows(TransactionAlreadyExistException.class, () -> {
            pb.addTransaction("User", p);
        });

        pb.createAccount("User1");
        Exception exception2 = assertThrows(AccountDoesNotExistException.class, () -> {
            pb.addTransaction("User1", new Transfer("Four", 100,"","I DO NOT EXIST","User"));
        });
        assertEquals(exception2.getMessage(), "Sender does not Exist");

        Exception exception3 = assertThrows(AccountDoesNotExistException.class, () -> {
            pb.addTransaction("User1", new Transfer("Four", 100,"","User1","I DO NOT EXIST"));
        });
        assertEquals(exception3.getMessage(), "Recipient does not Exist");

        pb.createAccount("User2");
        Exception exception4 = assertThrows(TransferNotValidException.class, () -> {
            pb.addTransaction("User1", new Transfer("Four", 100,"","User","User1"));
        });
        assertEquals(exception4.getMessage(), "You can only make Transfers for your own account");


    }

    @Test
    public void ContainsTransactionTest(){
        PrivateBank pb = new PrivateBank();
        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Payment("One", 1,"",1,1));
        list.add(new Payment("Two", 1,"",1,1));
        list.add(new Payment("Three", 1,"",1,1));
        pb.createAccount("User1", list);
        Payment p = new Payment("Three", 1,"",1,1);
        assertFalse(pb.containsTransaction("User1", p));
        pb.addTransaction("User1", p);
        assertTrue(pb.containsTransaction("User1", p));
    }

    @Test
    public void RemoveTransactionTest(){
        PrivateBank pb = new PrivateBank();
        Payment p = new Payment("Four", 1,"",1,1);
        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Payment("One", 1,"",1,1));
        list.add(new Payment("Two", 1,"",1,1));
        list.add(new Payment("Three", 1,"",1,1));
        pb.createAccount("User1", list);

        Exception exception = assertThrows(AccountDoesNotExistException.class, () -> {
            pb.removeTransaction("User", p);
        });

        Exception exception1 = assertThrows(TransactionDoesNotExistException.class, () -> {
            pb.removeTransaction("User1", p);
        });

        pb.addTransaction("User1", p);
        assertTrue(pb.containsTransaction("User1", p));
        pb.removeTransaction("User1",p);
        assertFalse(pb.containsTransaction("User1", p));
    }

    @Test
    public void GetAccountBalanceTest(){
        PrivateBank pb = new PrivateBank();
        pb.setIncomingInterest(0.05);
        pb.setOutgoingInterest(0.01);
        pb.createAccount("User");
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
    }

    @Test
    public void GetTransactionsSortedTest(){
        PrivateBank pb = new PrivateBank();
        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Payment("One", 1000,"",1,1));
        list.add(new Payment("Two", 3000,"",1,1));
        list.add(new Payment("Three", 2000,"",1,1));
        pb.createAccount("User1", list);
        assertTrue(pb.getTransactionsSorted("User1", true).get(0).getAmount() == 1000);
        assertTrue(pb.getTransactionsSorted("User1", true).get(1).getAmount() == 2000);
        assertTrue(pb.getTransactionsSorted("User1", true).get(2).getAmount() == 3000);
        assertTrue(pb.getTransactionsSorted("User1", false).get(0).getAmount() == 3000);
        assertTrue(pb.getTransactionsSorted("User1", false).get(1).getAmount() == 2000);
        assertTrue(pb.getTransactionsSorted("User1", false).get(2).getAmount() == 1000);
    }

    @Test
    public void getTransactionsByTypeTest(){
        PrivateBank pb = new PrivateBank();
        ArrayList<Transaction> list = new ArrayList<>();
        list.add(new Payment("One", 1000,"",1,1));
        list.add(new Payment("Two", -3000,"",1,1));
        list.add(new Payment("Three", 2000,"",1,1));
        pb.createAccount("User1", list);
        assertTrue(pb.getTransactionsByType("User1", true).get(0).getAmount() == 1000);
        assertTrue(pb.getTransactionsByType("User1", true).get(1).getAmount() == 2000);
        assertFalse(pb.getTransactionsByType("User1", true).contains(list.get(1)));
        assertTrue(pb.getTransactionsByType("User1", false).get(0).getAmount() == -3000);
        assertFalse(pb.getTransactionsByType("User1", false).contains(list.get(0)));
        assertFalse(pb.getTransactionsByType("User1", false).contains(list.get(2)));
    }
}