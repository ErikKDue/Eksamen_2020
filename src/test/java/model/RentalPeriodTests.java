package model;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RentalPeriodTests {


    @Test
    void Constructor_Test(){
        MotorHome testMotorHome = new MotorHome("BAPARALARP", "COOLCAR", "Super Prototype", 3, 40);
        Customer testCustomer = new Customer(23, "boop", "barp", "Barkyroad 7", "555-49141399", "boopbarp@email.com");

        RentalPeriod testRentalPeriod = new RentalPeriod(testCustomer, testMotorHome, 7, 7, LocalDate.parse("2007-03-03"), LocalDate.parse("2007-03-15"));
        System.out.println(testRentalPeriod.dateList.size());
        assertEquals(testRentalPeriod.totalExpectedPrice, 676+9.8);
    }
}
