import java.math.BigDecimal;

public class CreditCardPayment implements Payable {
    public CreditCardPayment(){

    }
 @Override
       public String processPayment (BigDecimal amount){
        System.out.println("Processing credit card payment of: " + amount + "$");
            return "CC-" + System.currentTimeMillis();
        }
       
}
