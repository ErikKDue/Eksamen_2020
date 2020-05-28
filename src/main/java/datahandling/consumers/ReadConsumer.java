package datahandling.consumers;

import datahandling.GenericMapper;
import model.IStoreable;
import java.util.function.Consumer;

public class ReadConsumer implements StoreableConsumer {
    IStoreable object;
    Consumer<IStoreable> readStoreableInDB = iStoreable ->  setList(GenericMapper.read(iStoreable)); //must have create method
    IStoreable result;

    private void setList(IStoreable result){
        this.result = result;
    }
    public IStoreable getResult(){
        return result;
    }
    public ReadConsumer(IStoreable object) {
        this.object = object;
    }
    @Override
    public boolean execute() {
        readStoreableInDB.accept(object);

        return true;
    }
    @Override
    public IStoreable getStorable() {
        return object;
    }
}
