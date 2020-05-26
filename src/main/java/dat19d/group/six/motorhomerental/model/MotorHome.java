package dat19d.group.six.motorhomerental.model;

public class MotorHome implements IStoreable{
    String registrationNumber;
    String brand; //enum?
    String model; //enum?
    int capacity;
    double basePrice; //er for LOW season

    enum VehicleStatus{
        AVAILABLE,
        MAINTENANCE,
        CLEANING,
        RENTED
    }
    VehicleStatus status; //status er sgudda ikke noget der skal forekomme separat fra en dato. Måske nærmere en hashmap med dato og tilhørende  status?

 public MotorHome(String registrationNumber, String brand, String Model, int capacity, double basePrice){
     this.registrationNumber = registrationNumber;
     this.brand = brand;
     this.model = Model;
     this.capacity = capacity;
     this.basePrice = basePrice;
     this.status = VehicleStatus.AVAILABLE;
 }
}
