package datahandling.consumers;

import datahandling.GenericMapper;
import model.IStoreable;

import java.util.function.Consumer;

public class DeleteConsumer implements StoreableConsumer {
    IStoreable object;
    Consumer<IStoreable> deleteStoreableInDB = GenericMapper::delete; //must have create methiod

    public DeleteConsumer(IStoreable object) {
        this.object = object;
    }
    public IStoreable getResult(){return object;}

    @Override
    public boolean execute() {
        deleteStoreableInDB.accept(object);

        return true;
    }
    @Override
    public IStoreable getStorable() {
        return object;
    }
}
