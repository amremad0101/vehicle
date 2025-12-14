import java.math.BigDecimal;

public class CashPayment implements Payable {
    public CashPayment(){
        
    }
    @Override
    public String processPayment(BigDecimal amount) {
        System.out.println("Payment of " + amount + "$ received in Cash.");
        return  "CASH-" + System.currentTimeMillis();

    }
}