
import java.util.*;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RentalSystem system = new RentalSystem();

    
        system.addVehicle(new Car("C1", "Toyota", "Corolla", 500, "Automatic", 0.0, "Economy"));
        system.addVehicle(new Car("C2", "BMW", "320i", 900, "Automatic", 0.1, "Luxury"));
    
        // Motorcycle Constructor: (id, brand, model, price)
        system.addVehicle(new Motorcycle("M1", "Yamaha", "R3", 300));

        // Bic Constructor: (id, brand, model, price)
        system.addVehicle(new Bic("B1", "Giant", "Escape", 50));

        // ----------------------------------------------------
        // 2. SEED USERS 
        // ----------------------------------------------------
        Admin admin = new Admin("admin", "1234", "0100000000");
        system.addCustomer(new Customer("Omar", "pass", "0111111111"));

        boolean running = true;

        while (running) {
            System.out.println("\n===== Vehicle Rental System =====");
            System.out.println("1) Login as Admin");
            System.out.println("2) Login as Customer");
            System.out.println("3) Exit");
            System.out.print("Choose: ");

            
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.next(); // consume invalid input
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> adminFlow(sc, system, admin);
                case 2 -> customerFlow(sc, system);
                case 3 -> {
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                }
                default -> System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }

    // ============================================================
    // Admin Flow
    // ============================================================
    public static void adminFlow(Scanner sc, RentalSystem system, Admin admin) {
        boolean inAdmin = true;

        while (inAdmin) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1) Add Vehicle");
            System.out.println("2) Remove Vehicle");
            System.out.println("3) View All Vehicles");
            System.out.println("4) Add Customer");
            System.out.println("5) Delete Customer");
            System.out.println("6) Sort Vehicles");
            System.out.println("7) Logout");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addVehicleMenu(sc, system);
                case 2 -> {
                    System.out.print("Enter Vehicle ID to remove: ");
                    String id = sc.nextLine();
                    system.deleteVehicle(id);
                }
                case 3 -> system.displayAllVehicles();
                case 4 -> {
                    System.out.print("Username: ");
                    String u = sc.nextLine();
                    System.out.print("Password: ");
                    String p = sc.nextLine();
                    System.out.print("Phone: ");
                    String ph = sc.nextLine();
                    system.addCustomer(new Customer(u, p, ph));
                }
                case 5 -> {
                    System.out.print("Enter phone to delete: ");
                    String ph = sc.nextLine();
                    system.deleteCustomer(ph);
                }
                case 6 -> system.sortVehicles();
                case 7 -> inAdmin = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ============================================================
    // Customer Flow
    // ============================================================
    public static void customerFlow(Scanner sc, RentalSystem system) {

        System.out.print("Enter your phone to login: ");
        String phone = sc.nextLine();

        Customer currentCustomer = system.findCustomer(phone);

        if (currentCustomer == null) {
            System.out.println("Customer not found! Please ask Admin to register you.");
            return;
        }

        System.out.println("Welcome back, " + currentCustomer.getname());

        boolean inCust = true;

        while (inCust) {
            System.out.println("\n===== Customer Menu =====");
            System.out.println("1) View Available Vehicles");
            System.out.println("2) Book Vehicle");
            System.out.println("3) View My Bookings (Optional)"); 
            System.out.println("4) Logout");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> system.displayAvailableVehicles();
                case 2 -> bookFlow(sc, system, currentCustomer);
                case 3 -> System.out.println("Feature coming soon...");
                case 4 -> inCust = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ============================================================
    // Add Vehicle Menu (Modified for your Constructors)
    // ============================================================
    private static void addVehicleMenu(Scanner sc, RentalSystem system) {
        System.out.println("1) Add Car");
        System.out.println("2) Add Motorcycle");
        System.out.println("3) Add Bicycle");
        System.out.print("Choose Type: ");

        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Brand: ");
        String brand = sc.nextLine();

        System.out.print("Enter Model: ");
        String model = sc.nextLine();

        System.out.print("Enter Price/Day: ");
        double price = sc.nextDouble();
        sc.nextLine(); // consume newline

        if (type == 1) { // CAR
            System.out.print("Transmission (Auto/Manual): ");
            String trans = sc.nextLine();
            
            System.out.print("Class (Economy, Luxury, Sports): ");
            String carClass = sc.nextLine();

            System.out.print("Discount (e.g. 0.1 for 10%): ");
            double discount = sc.nextDouble();
            sc.nextLine();

            system.addVehicle(new Car(id, brand, model, price, trans, discount, carClass));
        }
        else if (type == 2) { // MOTORCYCLE
            system.addVehicle(new Motorcycle(id, brand, model, price));
            System.out.println("Motorcycle added.");
        }
        else if (type == 3) { // BICYCLE
            system.addVehicle(new Bic(id, brand, model, price));
            System.out.println("Bicycle added.");
        }
        else {
            System.out.println("Invalid vehicle type!");
        }
    }

    // ============================================================
    // Booking Flow (Critical Logic)
    // ============================================================
    private static void bookFlow(Scanner sc, RentalSystem system, Customer cust) {

        system.displayAvailableVehicles();

        System.out.print("\nEnter Vehicle ID to book: ");
        String vid = sc.nextLine();

        Vehicle v = system.findVehicle(vid);

        if (v == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        if (!v.isAvailable()) {
            System.out.println("Sorry, this vehicle is currently rented.");
            return;
        }

        System.out.print("Enter number of Days: ");
        int days = sc.nextInt();
        sc.nextLine();

        System.out.println("Choose Payment Method:");
        System.out.println("1) Cash");
        System.out.println("2) Credit Card");
        System.out.print("Choose: ");

        int pm = sc.nextInt();
        sc.nextLine();

        Payable pay;

        if (pm == 1) {
            pay = new CashPayment();
        } else {
            pay = new CreditCardPayment(); 
        }

        try {
            Booking b = system.createBooking(cust, v, days, pay);
            System.out.println("Booking created. Total Cost: " + b.getTotalCost());
            
            System.out.println("Processing payment...");
            b.confirmBooking();
            
            System.out.println(">>> Payment Successful! Booking Confirmed.");

        } catch (PaymentException e) {
            System.out.println("X Payment Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("X An error occurred: " + e.getMessage());
        }
    }
}