package dat19d.group.six.motorhomerental.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class CustomerTests {

    @Test
   void ConstructorTest(){
        Customer testCustomer = new Customer(23, "boop", "barp", "Barkyroad 7", "555-49141399", "boopbarp@email.com");

        assertEquals(testCustomer.customerID, 23);
        assertEquals(testCustomer.firstName, "boop");
        assertEquals(testCustomer.lastName, "barp");
        assertEquals(testCustomer.address, "Barkyroad 7");
        assertEquals(testCustomer.phone, "555-49141399");
        assertEquals(testCustomer.email, "boopbarp@email.com");
    }
}
