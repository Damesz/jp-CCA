package hu.zsolt.damu.jpmorgan.cca.test;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public abstract class MessageFactory {

    public abstract Message createMessage(MessageGroup group);
    public abstract Message createMessage(MessageGroup group, String name);
}
