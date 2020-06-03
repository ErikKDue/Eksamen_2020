package datahandling.consumers;

import dat19d.group.six.motorhomerental.model.IStoreable;
import datahandling.GenericMapper;

import java.util.function.Consumer;

public class UpdateConsumer implements StoreableConsumer {
    IStoreable object;
    Consumer<IStoreable> updateStoreableInDB= GenericMapper::update;

    public UpdateConsumer(IStoreable object) {
        this.object = object;
    }
    @Override
    public boolean execute() {
        updateStoreableInDB.accept(object);

        return true;
    }
    public IStoreable getResult(){return object;}

    @Override
    public IStoreable getStorable() {
        return object;
    }
}
