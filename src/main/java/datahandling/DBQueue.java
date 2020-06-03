package datahandling;

public class DBQueue {

    //eager instantiation singleton
    public static final DBQueue queueInstance = new DBQueue();

    private DBQueue(){}

    //needs to have a linkedList of consumers that it runs in sequence
    //So it needs to have a special addtolist method that also wakes it up to start executing
    //and when the list is emptied, the process stops again
}
