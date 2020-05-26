package datahandling;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

public class DBManagerTests {

    @Test
    public void getConnectionTest(){
    DBManager.getConnection();
    }
}
