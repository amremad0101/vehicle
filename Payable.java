import java.math.BigDecimal;

public interface Payable {
    String processPayment(BigDecimal amount);
}
