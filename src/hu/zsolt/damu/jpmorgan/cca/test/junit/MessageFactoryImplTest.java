package hu.zsolt.damu.jpmorgan.cca.test.junit;

import hu.zsolt.damu.jpmorgan.cca.test.Message;
import hu.zsolt.damu.jpmorgan.cca.test.MessageFactory;
import hu.zsolt.damu.jpmorgan.cca.test.MessageGroup;
import hu.zsolt.damu.jpmorgan.cca.test.impl.MessageFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class MessageFactoryImplTest {

    private static final MessageGroup TEST_GROUP = MessageGroup.GROUP_TEST;
    private static final String TEST_NAME = "Test Message";
    private MessageFactory messageFactory;

    @Before
    public void setUp() throws Exception {
        messageFactory = new MessageFactoryImpl();
    }

    @Test
    public void testCreateMessage() {
        Message msg = messageFactory.createMessage(TEST_GROUP);
        Message msg2 = messageFactory.createMessage(TEST_GROUP, TEST_NAME);

        assertTrue(msg.getGroup().equals(TEST_GROUP));
        assertTrue(msg2.getGroup().equals(TEST_GROUP));
        assertTrue(msg2.getName().equals(TEST_NAME));
        assertTrue(msg.getName().equals("unknown"));
    }
}
