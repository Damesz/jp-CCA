package hu.zsolt.damu.jpmorgan.cca.test.impl;

import hu.zsolt.damu.jpmorgan.cca.test.Message;
import hu.zsolt.damu.jpmorgan.cca.test.MessageGroup;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class MessageImpl extends Message {

    private boolean completed = false;
    private boolean isTest = false;

    public MessageImpl() {
        super();
    }

    public MessageImpl(MessageGroup group) {
        super(group);
    }

    public MessageImpl(MessageGroup group, String name) {
        super(group, name);
    }

    public MessageImpl(MessageGroup group, String name, String data) {
        super(group, name, data);
    }

    @Override
    public void completed() {
        if(!isCompleted() && (isTest || MessageGroup.GROUP_TEST != getGroup())) {
            completed = true;
        }
    }

    public boolean isTest() {
        return isTest;
    }

    public void setIsTest(boolean isTest) {
        this.isTest = isTest;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }
}
