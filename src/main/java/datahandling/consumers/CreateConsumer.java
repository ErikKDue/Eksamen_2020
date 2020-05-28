package datahandling.consumers;

import datahandling.GenericMapper;
import model.IStoreable;

import java.util.function.Consumer;

public class CreateConsumer implements StoreableConsumer{
IStoreable object;
Consumer<IStoreable> createStoreableInDB = GenericMapper::create; //must have create methiod

    public CreateConsumer(IStoreable object) {
        this.object = object;
    }

    @Override
    public boolean execute() {
        createStoreableInDB.accept(object);

        return true;
    }
    public IStoreable getResult(){return object;}

    @Override
    public IStoreable getStorable() {
        return object;
    }
}
