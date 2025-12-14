public class Admin extends User {

    public Admin(String username, String password, String phone) {
        super(username, password, phone);
    }

    // ======================================================
    // CUSTOMER MANAGEMENT
    // ======================================================

    public void addCustomer(RentalSystem system, Customer newCustomer) {
        boolean added = system.addCustomer(newCustomer);

        if (!added) {
            System.out.println("Customer already exists!");
        } else {
            System.out.println("Customer added successfully!");
        }
    }

    public void deleteCustomer(RentalSystem system, String phone) {
        boolean result = system.deleteCustomer(phone);

        if (!result) {
            System.out.println("Delete failed. Customer not found.");
        } else {
            System.out.println("Customer deleted successfully.");
        }
    }

    // ======================================================
    // VEHICLE MANAGEMENT
    // ======================================================

    public void addCar(RentalSystem system,String id, String brand, String model, double pricePerDay,  String transmissionType,double discount,String carClass) {

        Vehicle v = new Car(id, brand, model, pricePerDay,transmissionType,discount,carClass);
        boolean added = system.addVehicle(v);

        if (!added) {
            System.out.println("Car with same ID already exists!");
        } else {
            System.out.println("Car added successfully.");
        }
    }

    public void addMotorcycle(RentalSystem system, String id, String brand,
                              String model, double price) {

        Vehicle v = new Motorcycle(id, brand, model, price);
        boolean added = system.addVehicle(v);

        if (!added) {
            System.out.println("Motorcycle already exists!");
        } else {
            System.out.println("Motorcycle added successfully.");
        }
    }

    public void addBic(RentalSystem system, String id, String brand, String model,
                       double price) {

        Vehicle v = new Bic(id, brand, model, price);
        boolean added = system.addVehicle(v);

        if (!added) {
            System.out.println("Bicycle already exists!");
        } else {
            System.out.println("Bicycle added successfully.");
        }
    }

    public void deleteVehicle(RentalSystem system, String id) {
        boolean removed = system.deleteVehicle(id);

        if (!removed) {
            System.out.println("Vehicle not found.");
        } else {
            System.out.println("Vehicle removed successfully.");
        }
    }

    public void viewAllVehicles(RentalSystem system) {
        system.displayAllVehicles();
    }

    public void viewAvailableVehicles(RentalSystem system) {
        system.displayAvailableVehicles();
    }

    public void sortVehicles(RentalSystem system) {
        system.sortVehicles();
    }
}
