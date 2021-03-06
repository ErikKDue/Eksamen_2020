package datahandling;

import dat19d.group.six.motorhomerental.model.IStoreable;
import datahandling.consumers.ConsumerGenerator;
import datahandling.consumers.StoreableConsumer;
import dat19d.group.six.motorhomerental.model.Customer;
import dat19d.group.six.motorhomerental.model.MotorHome;
import dat19d.group.six.motorhomerental.model.RentalPeriod;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GenericMapperTests {

    @Test
    public void When_Asking_ForCreate_Consumer_Get_CreateConsumer(){
        Customer testCustomer = new Customer(23, "boop", "barp", "Barkyroad 7", "555-49141399", "boopbarp@email.com");
        StoreableConsumer testObject = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.CREATE);
        assertNotNull(testObject);
        assertEquals(testCustomer, testObject.getStorable());
    }

    @Test
    public void When_Executing_Create_Consumer_Object_Is_Saved(){
        Customer testCustomer = new Customer(23, "boop", "barp", "Barkyroad 7", "555-49141399", "boopbarp@email.com");
        StoreableConsumer createTestObject = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.CREATE);
        assertTrue(createTestObject.execute()); //create
        StoreableConsumer readTestObject = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.READ);
        assertTrue(readTestObject.execute()); //read
        System.out.println(readTestObject.getResult());

        StoreableConsumer deleteTestObject = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.DELETE);
        assertTrue(deleteTestObject.execute());//delete
        //read
    }

    @Test
    public void When_Given_Object_With_Annotations_Generate_Correct_SQL(){
        Customer testCustomer = new Customer(27, "boop", "barp", "Barkyroad 7", "555-49141399", "boopbarp@email.com");
        MotorHome testMotorHome = new MotorHome("BAPARALARP", "COOLCAR", "Super Prototype", 3, 4000);
        RentalPeriod testRentalPeriod = new RentalPeriod(270, testCustomer, testMotorHome, 7, 7, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-03-03"));

        StoreableConsumer createTestCustomer = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.CREATE);
        StoreableConsumer createTestMotorHome = ConsumerGenerator.getConsumer(testMotorHome, ConsumerGenerator.CREATE);
        StoreableConsumer createTestRentalPeriod = ConsumerGenerator.getConsumer(testRentalPeriod, ConsumerGenerator.CREATE);

        createTestCustomer.execute();
        createTestMotorHome.execute();
        createTestRentalPeriod.execute();

        StoreableConsumer deleteTestRentalPeriod = ConsumerGenerator.getConsumer(testRentalPeriod, ConsumerGenerator.DELETE);
        deleteTestRentalPeriod.execute();
        StoreableConsumer deleteTestMotorHome = ConsumerGenerator.getConsumer(testMotorHome, ConsumerGenerator.DELETE);
        deleteTestMotorHome.execute();
        StoreableConsumer deleteTestCustomer = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.DELETE);
        deleteTestCustomer.execute();
    }

    @Test
    public void When_Creating_An_Object_In_DB_Update_Changes_It(){
        Customer testCustomer = new Customer(27, "boop", "barp", "Barkyroad 7", "555-49141399", "boopbarp@email.com");
        Customer testCustomer2 = new Customer(27, "booooooooooooo", "barp", "Duemosevej", "555-49141399", "boopbarp@email.com");


        StoreableConsumer createTestCustomer = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.CREATE);
        createTestCustomer.execute();

        StoreableConsumer updateTestCustomer = ConsumerGenerator.getConsumer(testCustomer2, ConsumerGenerator.UPDATE);
        updateTestCustomer.execute();

        Customer readCustomer = (Customer)ConsumerGenerator.getAndRunConsumer(new Customer(27), ConsumerGenerator.READ);

        StoreableConsumer deleteTestCustomer = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.DELETE);
        deleteTestCustomer.execute();
    }

    @Test
    public void Reading_Object_From_Primary_Key_Works(){
        Customer testCustomer = new Customer(27, "boop", "barp", "Barkyroad 7", "555-49141399", "boopbarp@email.com");



        StoreableConsumer createTestCustomer = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.CREATE);
        createTestCustomer.execute();


        StoreableConsumer readTestCustomer = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.READ);
        readTestCustomer.execute();
        IStoreable result = readTestCustomer.getResult();
        IStoreable result2 = ConsumerGenerator.getAndRunConsumer(testCustomer, ConsumerGenerator.READ);
        //needs to read it in here

        StoreableConsumer deleteTestCustomer = ConsumerGenerator.getConsumer(testCustomer, ConsumerGenerator.DELETE);
        deleteTestCustomer.execute();

    }


    @Test
    public void Creating_And_Reading_MotorHome(){
        MotorHome inputMotorHome = new MotorHome("BAPARALARP", "COOLCAR", "Super Prototype", 3, 4000);
        ConsumerGenerator.getAndRunConsumer(inputMotorHome, ConsumerGenerator.CREATE);

        MotorHome outputMotorHome = (MotorHome)ConsumerGenerator.getAndRunConsumer(new MotorHome("BAPARALARP"), ConsumerGenerator.READ);


        assertEquals(inputMotorHome.getCapacity(), outputMotorHome.getCapacity());
        assertEquals(inputMotorHome.getModel(), outputMotorHome.getModel());

        ConsumerGenerator.getAndRunConsumer(new MotorHome("BAPARALARP"), ConsumerGenerator.DELETE);
    }
    /* //I don't think you can separate create rental period from creating the other objects since ti uses them as foreign keys.
    @Test
    public void Create_Rental_Period(){
        Customer testCustomer = new Customer(27, "boop", "barp", "Barkyroad 7", "555-49141399", "boopbarp@email.com");
        MotorHome testMotorHome = new MotorHome("BAPARALARP", "COOLCAR", "Super Prototype", 3, 4000);
        RentalPeriod testRentalPeriod = new RentalPeriod(270, testCustomer, testMotorHome, 7, 7, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-03-03"));

        StoreableConsumer createTestRentalPeriod = ConsumerGenerator.getConsumer(testRentalPeriod, ConsumerGenerator.CREATE);
        createTestRentalPeriod.execute();
    }   */
}

