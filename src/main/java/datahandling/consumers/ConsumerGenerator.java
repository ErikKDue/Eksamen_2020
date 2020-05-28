package datahandling.consumers;

import dat19d.group.six.motorhomerental.model.IStoreable;

import java.security.InvalidParameterException;

public class ConsumerGenerator {
    public static final String CREATE = "create";
    public static final String READ = "read";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";


    public static StoreableConsumer getConsumer(IStoreable object, String type){
        if(type.equals(CREATE)) return new CreateConsumer(object);
        if(type.equals(READ)) return new ReadConsumer(object);
        if(type.equals(DELETE)) return new DeleteConsumer(object);
        if(type.equals(UPDATE)) return new UpdateConsumer(object);
        return null;
    }
}
