public class Car extends Vehicle {
    private int seats;
    private String transmissionType; // electrical / fuel
    private String carClass;// Economy, Standard, Luxury, Sports
    private double discount;
    public Car(String id, String brand, String model, double pricePerDay,  String transmissionType,double discount,String carClass) {
        super(id, brand, model, pricePerDay);
       this.transmissionType=transmissionType;
        this.carClass=carClass;

    }

    @Override
public double calculateCost(int days) {
    if (getDiscountedPrice() ==0){
        return days * getClassMultiplier();
    }
    else{
    return getDiscountedPrice() * days * getClassMultiplier();
}}
        public double getClassMultiplier() {
    return switch (carClass) {
        case "Luxury" -> 1.4;
        case "Sports" -> 1.6;
        case "Economy" -> 1.0;
        default -> 1.2; // Standard
    };
}

    @Override
    public String toString() {
        return "Car: " + super.toString() +
                " | Seats: " + seats +
                " | Transmission: " + transmissionType;
    }

      public static Car addcar(String id, String brand, String model, double pricePerDay,  String transmissionType,double discount,String carClass) {
      return new Car(id, brand, model, pricePerDay,transmissionType,discount,carClass ); 
}
 public String getCarClass() {return carClass;}
 public double getDiscountedPrice() {return discount;}
}

