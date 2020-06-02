package dat19d.group.six.motorhomerental.Controller;

import dat19d.group.six.motorhomerental.model.Customer;
import datahandling.consumers.ConsumerGenerator;
import datahandling.consumers.StoreableConsumer;

import java.sql.SQLException;

public class CustomerMapper {

    public void opret(Customer customer)
    {

        StoreableConsumer createCustomer = ConsumerGenerator.getConsumer(customer, ConsumerGenerator.CREATE);
        createCustomer.execute();
    }
}
