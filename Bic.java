public class Bic extends Vehicle {
    

    public Bic(String id, String brand, String model, double pricePerDay) {
        super(id, brand, model, pricePerDay);
       
    }

    @Override
    public double calculateCost(int days) {
        return pricePerDay * days*0.1; 
    }

    @Override
    public String toString() {
        return "Bicycle: " + super.toString() 
               ;
    }

       public static Bic addbic(String id, String brand, String model, double price) {
      return new Bic (id, brand, model, price); 
}

}
