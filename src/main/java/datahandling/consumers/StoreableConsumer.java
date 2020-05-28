package datahandling.consumers;

import model.IStoreable;

public interface StoreableConsumer {

    public boolean execute();

    public IStoreable getStorable();

    public IStoreable getResult();
}
