package hu.zsolt.damu.jpmorgan.cca.test.impl;

import hu.zsolt.damu.jpmorgan.cca.test.Message;
import hu.zsolt.damu.jpmorgan.cca.test.MessageFactory;
import hu.zsolt.damu.jpmorgan.cca.test.MessageGroup;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class MessageFactoryImpl extends MessageFactory {

    @Override
    public Message createMessage(MessageGroup group) {
        Message msg = new MessageImpl(group);
        return msg;
    }

    @Override
    public Message createMessage(MessageGroup group, String name) {
        Message msg = new MessageImpl(group, name);
        return msg;
    }
}
