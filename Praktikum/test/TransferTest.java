import bank.Transfer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransferTest {
    @Test
    public void TestCopyConstructor(){
        Transfer p1 = new Transfer("13.10.2021", 1000, "Test Incomming", "Sender", "Recepent");
        Transfer p2 = new Transfer(p1);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void TestCalculate(){
        Transfer p1 = new Transfer("13.10.2021", 1000, "Test Incomming", "Sender", "Recepent");
        double amountBefore = p1.getAmount();
        p1.calculate();
        assertEquals(p1.getAmount(), amountBefore);
    }

    @Test
    public void TestEquals(){
        Transfer p1 = new Transfer("13.10.2021", 1000, "Test Incomming", "Sender", "Recepent");
        Transfer p2 = new Transfer(p1);
        assertTrue(p1.equals(p2));
        p1.setAmount(0);
        assertFalse(p1.equals(p2));
        int i = 0;
        assertFalse(p1.equals(i));
    }
}
