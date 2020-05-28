package datahandling.consumers;

import dat19d.group.six.motorhomerental.model.IStoreable;

public interface StoreableConsumer {

    public boolean execute();

    public IStoreable getStorable();

    public IStoreable getResult();
}
