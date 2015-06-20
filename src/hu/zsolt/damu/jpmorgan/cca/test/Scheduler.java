package hu.zsolt.damu.jpmorgan.cca.test;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public abstract class Scheduler {

    public abstract void addMessage(Message msg);
    public abstract void start();
    public abstract void cancelGroup(MessageGroup group);
}
