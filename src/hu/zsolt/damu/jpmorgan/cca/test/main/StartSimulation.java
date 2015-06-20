package hu.zsolt.damu.jpmorgan.cca.test.main;

import hu.zsolt.damu.jpmorgan.cca.test.*;
import hu.zsolt.damu.jpmorgan.cca.test.impl.GatewayImpl;
import hu.zsolt.damu.jpmorgan.cca.test.impl.MessageFactoryImpl;
import hu.zsolt.damu.jpmorgan.cca.test.impl.ResourceManagerImpl;
import hu.zsolt.damu.jpmorgan.cca.test.impl.ResourceShedulerImpl;

import java.util.List;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class StartSimulation {


    private final static MessageGroup[] MESSAGES_TEST_CASES = {
            MessageGroup.GROUP_TWO,
            MessageGroup.GROUP_ONE,
            MessageGroup.GROUP_TWO,
            MessageGroup.GROUP_THREE
    };

    private static final String MESSAGE_NAME_PREFIX = "Test Message in ";

    public static void main(String args[]) throws InterruptedException {
        Gateway gateway = new GatewayImpl();
        ResourceManager resourceManager = new ResourceManagerImpl();
        ResourceScheduler resourceScheduler = new ResourceShedulerImpl(resourceManager, gateway);

        resourceScheduler.start();
        MessageFactory messageFactory = new MessageFactoryImpl();

        resourceManager.addResource("Init Resources");

        int count = 0;

        for (MessageGroup group: MESSAGES_TEST_CASES) {
            System.out.println(group);

            Message message = messageFactory.createMessage(group, count + 1 + ". " + MESSAGE_NAME_PREFIX + group.getName());
            resourceScheduler.addMessage(message);

            count++;
        }
    }

}
