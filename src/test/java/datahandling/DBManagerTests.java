package datahandling;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class DBManagerTests {

    @Test
    public void getConnectionTest(){
    Connection connection = DBManager.getConnection();
    assertNotNull(connection);
    }
}
