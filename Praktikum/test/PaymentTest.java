import bank.Payment;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    Payment p1;
    Payment p2;

    @BeforeEach
    public void init(){
        p1 = new Payment("13.10.2021", 1000, "Test Incomming", 0.05, 0.1);
        p2 = new Payment("13.10.2021", -1000, "Test Incomming", 0.05, 0.1);
    }

    @Test
    public void TestCopyConstructor(){
        Payment pCopy = new Payment(p1);
        assertEquals(p1,pCopy);
    }

    @Test
    public void TestCalculate(){
        p1.calculate();
        assertEquals(p1.calculate(), 950);
        p2.calculate();
        assertEquals(p2.calculate(), -1100);
    }

    @Test
    public void TestEqual() {
        assertFalse(p1.equals(p2));
        p1.setAmount(p2.getAmount());
        assertEquals(p1,p2);
    }
}
