import bank.IncomingTransfer;
import bank.OutgoingTransfer;
import bank.Transfer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransferTest {

    Transfer p1;

    @BeforeEach
    public void init(){
        p1 = new Transfer("13.10.2021", 1000, "Test Incomming", "Sender", "Recepent");
    }

    @Test
    public void TestCopyConstructor(){
        assertEquals(p1, new Transfer(p1));
    }

    @Test
    public void TestCalculate(){
        IncomingTransfer ic = new IncomingTransfer(p1);
        OutgoingTransfer oc = new OutgoingTransfer(p1);
        assertEquals(1000, ic.calculate());
        assertEquals(-1000, oc.calculate());
    }

    @Test
    public void TestEquals(){
        Transfer p2 = new Transfer("13.10.2021", 1000, "Test Incomming", "Sender", "Recepent");
        assertTrue(p1.equals(p2));
        p1.setAmount(0);
        assertFalse(p1.equals(p2));
        int i = 0;
        assertFalse(p1.equals(i));
    }

}
