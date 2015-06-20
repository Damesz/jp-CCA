package hu.zsolt.damu.jpmorgan.cca.test.impl;

import hu.zsolt.damu.jpmorgan.cca.test.Gateway;
import hu.zsolt.damu.jpmorgan.cca.test.Message;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class GatewayImpl implements Gateway {

    @Override
    public void send(Message msg) {
        if(!msg.isCompleted()) {
            msg.completed();
        }
    }
}
