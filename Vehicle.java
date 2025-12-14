public abstract class Vehicle implements Comparable<Vehicle> {
    protected String id;
    protected String brand;
    protected String model;
    protected double pricePerDay;
    protected boolean isAvailable;

    public Vehicle(String id, String brand, String model, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;   // default available
    }

    //Abstract metho
    public abstract double calculateCost(int days);

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean status) {
        this.isAvailable = status;
    }

    public String getId() {
        return id;
    }

    // For sorting by price
    @Override
    public int compareTo(Vehicle other) {
        return Double.compare(this.pricePerDay, other.pricePerDay);
    }

    @Override
    public String toString() {
        return brand + " " + model + " | Price/day: " + pricePerDay + " | Available: " + isAvailable;
    }
    
}
