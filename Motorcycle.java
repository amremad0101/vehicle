public class Motorcycle extends Vehicle {
    

    public Motorcycle(String id, String brand, String model, double pricePerDay) {
        super(id, brand, model, pricePerDay);
       
    }

    @Override
    public double calculateCost(int days) {
        return (pricePerDay * days) * 0.5; 
    }

    @Override
    public String toString() {
        return "Motorcycle: " + super.toString() 
                ;
    }
          public static Motorcycle addmotorcycle(String id, String brand, String model, double price) {
      return new Motorcycle (id, brand, model, price); 
}
}  
