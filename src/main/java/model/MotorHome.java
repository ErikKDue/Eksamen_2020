package model;

import annotations.StoreableAttribute;

public class MotorHome implements IStoreable{
    @StoreableAttribute
    String registrationNumber;
    @StoreableAttribute
    String brand; //enum?
    @StoreableAttribute
    String model; //enum?
    @StoreableAttribute
    int capacity;
    @StoreableAttribute
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

    @Override //returns the name of the SQL TABLE
    public String getType() {
        return "motor_home";
    }
}
