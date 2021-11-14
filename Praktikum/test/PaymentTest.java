import bank.Payment;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void TestCopyConstructor(){
        Payment p1 = new Payment("13.10.2021", 1000, "Test Incomming", 0.05, 0.1);
        Payment p2 = new Payment(p1);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void TestCalculate(){
        Payment p1 = new Payment("13.10.2021", 1000, "Test Incomming", 0.05, 0.1);
        p1.calculate();
        assertEquals(p1.calculate(), 950);
        Payment p2 = new Payment("13.10.2021", -1000, "Test Incomming", 0.05, 0.1);
        p2.calculate();
        assertEquals(p2.calculate(), -1100);
    }

    @Test
    public void TestEqual(){
        Payment p1 = new Payment("13.10.2021", 1000, "Test Incomming", 0.05, 0.1);
        Payment p2 = new Payment(p1);
        assertTrue(p1.equals(p2));

        p1.setAmount(0);
        assertFalse(p1.equals(p2));

        int i = 0;
        assertFalse(p1.equals(i));

        Payment p3 = null;
        assertFalse(p1.equals(p3));
    }
}
