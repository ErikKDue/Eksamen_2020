package model;

import annotations.StoreableAttribute;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentalPeriod implements IStoreable{
    @StoreableAttribute
    int ID;
    @StoreableAttribute
    int customerID;
    Customer customer;
    @StoreableAttribute
    String registrationNumber;
    MotorHome motorHome;
    @StoreableAttribute
    LocalDate startDate;
    @StoreableAttribute
    LocalDate endDate;

    int pickupPointDistance; //we'd need Google Maps integration and A* or Dijkstra or some other pathfinding algorithm to figure this out automatically. Just have the user enter it manually for now.

    int dropoffPointDistance;

    double totalExpectedPrice;

    int maxKMBeforeSurcharge; //is 400 * rentalPeriod.Length
    List<LocalDate> dateList; //maybe just startDate and endDate?
    ArrayList<Object> Extras; //probably needs an extras enum or class with prices

    public RentalPeriod(Customer customer, MotorHome motorHome, int pickupPointDistance, int dropoffPointDistance, @org.jetbrains.annotations.NotNull LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateList = startDate.datesUntil(endDate).collect(Collectors.toList());
        this.dateList.add(endDate);
        this.customer = customer;
        this.customerID = customer.customerID;
        this.motorHome = motorHome;
        this.registrationNumber = motorHome.registrationNumber;
        this.pickupPointDistance = pickupPointDistance;
        this.dropoffPointDistance = dropoffPointDistance;
        this.maxKMBeforeSurcharge = dateList.size()*400;
        this.totalExpectedPrice = this.calculateExpectedPrice();
    }

    public RentalPeriod(int ID, Customer customer, MotorHome motorHome, int pickupPointDistance, int dropoffPointDistance, @org.jetbrains.annotations.NotNull LocalDate startDate, LocalDate endDate){
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateList = startDate.datesUntil(endDate).collect(Collectors.toList());
        this.dateList.add(endDate);
        this.customer = customer;
        this.customerID = customer.customerID;
        this.motorHome = motorHome;
        this.registrationNumber = motorHome.registrationNumber;
        this.pickupPointDistance = pickupPointDistance;
        this.dropoffPointDistance = dropoffPointDistance;
        this.maxKMBeforeSurcharge = dateList.size()*400;
        this.totalExpectedPrice = this.calculateExpectedPrice();
    }

    public double calculateExpectedPrice(){
        double returnValue = 0;
        //for each day in rentalPeriod, check season and add (MotorHome.basePrice* 1, 1.3 or 1.6)
        for (LocalDate date: dateList) {
            switch (SeasonChecker.evaluateSeason(date)) {
                case PEAK:
                    returnValue += motorHome.basePrice * 1.6;
                    break;
                case MID:
                    returnValue += motorHome.basePrice * 1.3;
                    break;
                case LOW:
                    returnValue += motorHome.basePrice * 1;
                    break;
                default:
                    returnValue += motorHome.basePrice * 1.3;
                    break;
            }
        }
        //add price for extras
        //add price for pickupPointDistance and dropoffPointDistance(km*.7 euro)
        returnValue += (pickupPointDistance+dropoffPointDistance)*.7;

        return returnValue;
    }

    public double calculateCancellationPrice(){
        double returnValue = calculateExpectedPrice();
        //find out how long until start date
        int howLongTillStartDate = 0;//needs a method to figger it out
       if (howLongTillStartDate> 49){
           returnValue = returnValue * .2;
       }
       else if (howLongTillStartDate> 15){
           returnValue = returnValue * .5;
       }
       else if (howLongTillStartDate> 1){
           returnValue = returnValue * .8;
       }
       else {
           returnValue = returnValue * .95;
       }

        if (returnValue>200)
            return returnValue;

        return 200;
    }

    public double calculateSurcharges(){
        //needs actual distance travelled, gas tank status, repair costs
        //excess driving = actual distance - (rental period length *400)
        //returnvalue = excess driving * 1 //* excessDrivingMultiplier? Customer will probably want ot change it.
        //if tankEmpty, returnValue+= 70
        //returnValue += repairCost;
        return 0;
    }
    @Override //returns the name of the SQL TABLE
    public String getType() {
        return "rental_period";
    }
}
