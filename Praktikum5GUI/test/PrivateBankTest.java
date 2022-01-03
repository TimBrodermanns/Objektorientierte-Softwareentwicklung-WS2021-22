import bank.PrivateBank;
import bank.exceptions.*;
import org.junit.jupiter.api.*;
import bank.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrivateBankTest {

    public PrivateBank privateBank;
    @BeforeEach
    public void init(){
        try {
            privateBank = new PrivateBank("Test", 0.1, 0.1);
            privateBank.reset();
            privateBank.createAccount("User");
            privateBank.addTransaction("User", new Payment("twice", 1, "", 1, 1));
            privateBank.createAccount("User1");
            privateBank.addTransaction("User1", new Payment("One", 1000, "", 1, 1));
            privateBank.addTransaction("User1", new Payment("Two", 3000, "", 1, 1));
            privateBank.addTransaction("User1", new Payment("Three", 2000, "", 1, 1));
            privateBank.createAccount("User2");
            privateBank.addTransaction("User2", new Payment("One", 1000, "", 1, 1));
            privateBank.addTransaction("User2", new Payment("Two", -3000, "", 1, 1));
            privateBank.addTransaction("User2", new Payment("Three", 2000, "", 1, 1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void EqualTest(){
        PrivateBank pb = new PrivateBank("Test", 0.1,0.1);
        pb.reset();
        try{
            pb.createAccount("User");
            pb.addTransaction("User", new Payment("twice", 1,"",1,1));
            pb.createAccount("User1");
            pb.addTransaction("User1",new Payment("One", 1000,"",1,1));
            pb.addTransaction("User1",new Payment("Two", 3000,"",1,1));
            pb.addTransaction("User1",new Payment("Three", 2000,"",1,1));
            pb.createAccount("User2");
            pb.addTransaction("User2",new Payment("One", 1000,"",1,1));
            pb.addTransaction("User2",new Payment("Two", -3000,"",1,1));
            pb.addTransaction("User2",new Payment("Three", 2000,"",1,1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals(pb, privateBank);
        pb.createAccount("User3");
        assertNotEquals(pb, privateBank);
    }

    @Test
    public void CopyConstructorTest(){
        assertEquals(privateBank,new PrivateBank(privateBank));
    }

    /*
    @ParameterizedTest
    @ValueSource(strings = {"newUser", "newUser1", "newUser2", "newUser3"})
    public void AccountCreationTest(String Account){
        privateBank.createAccount(Account);
        Exception exception = assertThrows(AccountAlreadyExistsException.class, () -> {
            privateBank.createAccount(Account);
        });
    }
    /*
    @Test
    public void AddTransactionTest(){
        Exception exception = assertThrows(AccountDoesNotExistException.class, () -> {
            privateBank.addTransaction("User10", new Payment("twice", 1,"",1,1));
        });
        Exception exception1 = assertThrows(TransactionAlreadyExistException.class, () -> {
            privateBank.addTransaction("User", privateBank.getTransactions("User").get(0));
        });

        Exception exception2 = assertThrows(AccountDoesNotExistException.class, () -> {
            privateBank.addTransaction("User1", new Transfer("Four", 100,"","I DO NOT EXIST","User"));
        });
        assertEquals(exception2.getMessage(), "Sender does not Exist");

        Exception exception3 = assertThrows(AccountDoesNotExistException.class, () -> {
            privateBank.addTransaction("User1", new Transfer("Four", 100,"","User1","I DO NOT EXIST"));
        });
        assertEquals(exception3.getMessage(), "Recipient does not Exist");

        Exception exception4 = assertThrows(TransferNotValidException.class, () -> {
            privateBank.addTransaction("User1", new Transfer("Four", 100,"","User","User1"));
        });
        assertEquals(exception4.getMessage(), "You can only make Transfers for your own account");
    }
    */

    @Test
    public void ContainsTransactionTest(){
        Payment p0 = new Payment("Once", 1,"",1,1);
        Payment p1 = new Payment("Twice", 1,"",1,1);
        try {
            privateBank.addTransaction("User", p0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertTrue(privateBank.containsTransaction("User", p0));
        assertFalse(privateBank.containsTransaction("User",p1));
    }

    /*
    @Test
    public void RemoveTransactionTest(){
        Payment p0 = new Payment("Once", 1,"",1,1);
        Exception exception = assertThrows(AccountDoesNotExistException.class, () -> { privateBank.removeTransaction("User10", p0); });
        Exception exception1 = assertThrows(TransactionDoesNotExistException.class, () -> { privateBank.removeTransaction("User1", p0); });
        try{
            privateBank.addTransaction("User1", p0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertTrue(privateBank.containsTransaction("User1", p0));
        try {
            privateBank.removeTransaction("User1", p0);
        }catch (Exception e){}
        assertFalse(privateBank.containsTransaction("User1", p0));
    }

     */

    @Test
    public void GetAccountBalanceTest(){
        assertEquals(0.9, privateBank.getAccountBalance("User"));
        assertEquals(5400.0,privateBank.getAccountBalance("User1"));
        assertEquals(-600.0, privateBank.getAccountBalance("User2"));
    }

    @Test
    public void GetTransactionsSortedTest(){
        assertTrue(privateBank.getTransactionsSorted("User1", true).get(0).getAmount() == 1000);
        assertTrue(privateBank.getTransactionsSorted("User1", true).get(1).getAmount() == 2000);
        assertTrue(privateBank.getTransactionsSorted("User1", true).get(2).getAmount() == 3000);
        assertTrue(privateBank.getTransactionsSorted("User1", false).get(0).getAmount() == 3000);
        assertTrue(privateBank.getTransactionsSorted("User1", false).get(1).getAmount() == 2000);
        assertTrue(privateBank.getTransactionsSorted("User1", false).get(2).getAmount() == 1000);
    }

    @Test
    public void getTransactionsByTypeTest(){
        List<Transaction> list = new ArrayList<Transaction>();
        list.add(new Payment("One", 1000,"",1,1));
        list.add(new Payment("Two", -3000,"",1,1));
        list.add(new Payment("Three", 2000,"",1,1));
        privateBank.createAccount("User3",list);
        assertTrue(privateBank.getTransactionsByType("User3", true).get(0).getAmount() == 1000);
        assertTrue(privateBank.getTransactionsByType("User3", true).get(1).getAmount() == 2000);
        assertFalse(privateBank.getTransactionsByType("User3", true).contains(list.get(1)));
        assertTrue(privateBank.getTransactionsByType("User3", false).get(0).getAmount() == -3000);
        assertFalse(privateBank.getTransactionsByType("User3", false).contains(list.get(0)));
        assertFalse(privateBank.getTransactionsByType("User3", false).contains(list.get(2)));
    }

    @Test
    public void TestJson() throws Exception{
        String expected = "[\n" +
                "  {\n" +
                "    \"CLASSNAME\": \"Payment\",\n" +
                "    \"INSTANCE\": {\n" +
                "      \"date\": \"twice\",\n" +
                "      \"amount\": 1.0,\n" +
                "      \"description\": \"\",\n" +
                "      \"IncomingInterest\": 0.1,\n" +
                "      \"OutgoingInterest\": 0.1\n" +
                "    }\n" +
                "  }\n" +
                "]";
        File file = new File(privateBank.getPath() + "\\User.json");
        assertEquals(expected, new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))));
    }

    /*
    @ParameterizedTest
    @ValueSource(strings = {"User.json", "User1.json", "User2.json"})
    public void TestAllAccountsSerialize(String jsonName){
        File f = new File(privateBank.getPath());
        boolean contains = false;
        for (File fi : f.listFiles()) {
            contains = contains || fi.getName().equals(jsonName);
        }
        assertTrue(contains);
    }
    */

    @Test
    public void TestGetAllUser(){
        assertTrue(privateBank.getAllAccounts().contains("User"));
        assertTrue(privateBank.getAllAccounts().contains("User1"));
        assertTrue(privateBank.getAllAccounts().contains("User2"));
    }

    @Test
    public void TestDeserialize(){
        //PrivateBank NewPrivateBank = new PrivateBank("Test", 0.1, 0.1);
        assertTrue(true);
    }
}