
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

// 1. Change: implements Comparable instead of Payable
public class Booking implements Comparable<Booking> {

    private final String bookingId;
    private final Customer customer;
    private final Vehicle vehicle;
    private final int days;
    private final int id;
    private final BigDecimal totalCost;
    private final Payable paymentMethod;

    private BookingStatus status;
    private String paymentRef;

    public Booking(int id,Customer cust, Vehicle veh, int days, Payable method) {
        this.bookingId = UUID.randomUUID().toString();
        this.customer = Objects.requireNonNull(cust, "customer is required");
        this.vehicle = Objects.requireNonNull(veh, "vehicle is required");
        this.paymentMethod = Objects.requireNonNull(method, "payment method is required");
        this.id=id;
        if (days <= 0) throw new IllegalArgumentException("days must be > 0");
        this.days = days;

        // Assuming calculateCost returns a double
        this.totalCost = BigDecimal.valueOf(veh.calculateCost(days));

        this.status = BookingStatus.PENDING;
    }
    public boolean isPaid() {
        return paymentRef != null;
    }

    public void confirmBooking() throws PaymentException {
        if (status == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Cannot confirm a cancelled booking");
        }
        if (!vehicle.isAvailable()) {
            throw new IllegalStateException("Vehicle is not available");
        }
        
        paymentRef = paymentMethod.processPayment(totalCost);
        
if (paymentRef != null) {
            this.status = BookingStatus.CONFIRMED;
            this.vehicle.setAvailable(false);

            System.out.println("Booking Confirmed for User " + customer.getname()
                    + " | Booking ID: " + bookingId
                    + " | PaymentRef: " + paymentRef);
        }
        else {
            // If it returns null, we assume payment failed
            throw new PaymentException("Payment failed: No transaction reference returned.");
        }

    }

    public void cancel() {
        if (status == BookingStatus.CONFIRMED) {
            vehicle.setAvailable(true);
        }
        status = BookingStatus.CANCELLED;
    }

    // Getters
    public String getBookingId() { return bookingId; }
    public int getCustomerID() { return id; }
    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }
    public int getDays() { return days; }
    public BigDecimal getTotalCost() { return totalCost; }
    public BookingStatus getStatus() { return status; }
    public String getPaymentRef() { return paymentRef; }

    // 2. Change: Added @Override annotation
    @Override
    public int compareTo(Booking other) {
        int cmp = this.totalCost.compareTo(other.totalCost);
        if (cmp != 0) return cmp;
        return this.bookingId.compareTo(other.bookingId);
    }
}