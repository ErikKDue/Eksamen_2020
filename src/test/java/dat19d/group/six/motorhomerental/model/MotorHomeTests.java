package dat19d.group.six.motorhomerental.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MotorHomeTests {

    @Test
    void ConstructorTest(){
        MotorHome testMotorHome = new MotorHome("BAPARALARP", "COOLCAR", "Super Prototype", 3, 4000);

        assertEquals(testMotorHome.registrationNumber, "BAPARALARP");
        assertEquals(testMotorHome.brand, "COOLCAR");
        assertEquals(testMotorHome.model, "Super Prototype");
        assertEquals(testMotorHome.capacity, 3);
        assertEquals(testMotorHome.basePrice, 4000);
        assertEquals(testMotorHome.status, MotorHome.VehicleStatus.AVAILABLE);

    }
}
