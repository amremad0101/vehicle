import java.util.*;

public class RentalSystem {

    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<Booking> bookings;

    public RentalSystem() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    // ======================================================
    // VEHICLES
    // ======================================================

    public boolean addVehicle(Vehicle v) {
        for (Vehicle x : vehicles) {
            if (x.getId().equals(v.getId())) {
                System.out.println("Vehicle with this ID already exists!");
                return false;
            }
        }
        vehicles.add(v);
        System.out.println("Vehicle added successfully.");
        return true;
    }

    public boolean deleteVehicle(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equals(id)) {
                vehicles.remove(v);
                System.out.println("Vehicle removed successfully.");
                return true;
            }
        }
        System.out.println("Vehicle NOT found.");
        return false;
    }

    public Vehicle findVehicle(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equals(id)) return v;
        }
        return null;
    }

    public void displayAvailableVehicles() {
        System.out.println("\n=== AVAILABLE VEHICLES ===");
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                System.out.println(v);
            }
        }
    }

    public void displayAllVehicles() {
        System.out.println("\n=== ALL VEHICLES ===");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    public void sortVehicles() {
        Collections.sort(vehicles); // من Comparable
        System.out.println("Vehicles sorted successfully.");
    }

    // ======================================================
    // CUSTOMERS
    // ======================================================

    public boolean addCustomer(Customer c) {
        for (Customer x : customers) {
            if (x.getphone().equals(c.getphone())) {
                System.out.println("Customer already exists!");
                return false;
            }
        }
        customers.add(c);
        System.out.println("Customer added.");
        return true;
    }

    public boolean deleteCustomer(String phone) {
        for (Customer c : customers) {
            if (c.getphone().equals(phone)) {
                customers.remove(c);
                System.out.println("Customer removed.");
                return true;
            }
        }
        System.out.println("Customer NOT found.");
        return false;
    }
        public Customer findCustomer(String phone) {
        for (Customer c : customers) {
            if (c.getphone().equals(phone)) return c;
        }
        return null;
    }

    // ======================================================
    // BOOKINGS
    // ======================================================

    public Booking createBooking(Customer cust, Vehicle vehicle, int days, Payable payment) {
        int id = bookings.size() + 1;

        Booking b = new Booking(id, cust, vehicle, days, payment);

        bookings.add(b);
        return b;
    }
        public boolean cancelBooking(String bookingId) {
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId)) {
                b.cancel();
                return true;
            }
        }
        return false;
    }
     public void displayBookings() {
        System.out.println("\n=== ALL BOOKINGS ===");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (Booking b : bookings) System.out.println(b.getBookingId() + " | " + b.getStatus());
    }
}
