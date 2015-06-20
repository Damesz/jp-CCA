package hu.zsolt.damu.jpmorgan.cca.test.junit;

import hu.zsolt.damu.jpmorgan.cca.test.Message;
import hu.zsolt.damu.jpmorgan.cca.test.MessageGroup;
import hu.zsolt.damu.jpmorgan.cca.test.impl.GatewayImpl;
import hu.zsolt.damu.jpmorgan.cca.test.impl.MessageImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class GatewayImplTest {

    private GatewayImpl gateway;
    private static final String TEST_MESSAGE_NAME = "Test Message";

    @Before
    public void setUp() throws Exception {
        gateway = new GatewayImpl();
    }

    @Test
    public void testSend() {
        Message msg = new MessageImpl(MessageGroup.GROUP_ONE, TEST_MESSAGE_NAME);
        gateway.send(msg);
        assertTrue(msg.isCompleted());

        System.out.print(msg.sentMessage());
    }
}
