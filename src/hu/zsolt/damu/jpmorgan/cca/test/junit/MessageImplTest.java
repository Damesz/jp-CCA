package hu.zsolt.damu.jpmorgan.cca.test.junit;

import hu.zsolt.damu.jpmorgan.cca.test.Message;
import hu.zsolt.damu.jpmorgan.cca.test.MessageGroup;
import hu.zsolt.damu.jpmorgan.cca.test.impl.MessageImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class MessageImplTest {

    private static final String TEST_NAME = "Test Message";
    private static final MessageGroup TEST_GROUP = MessageGroup.GROUP_TEST;
    private Message msg;

    @Before
    public void setUp() throws Exception {
        msg = new MessageImpl(TEST_GROUP, TEST_NAME);
    }

    @Test
    public void testCompleted() {
        assertFalse(msg.isCompleted());
        msg.completed();
        assertFalse(msg.isCompleted());
    }

    @Test
    public void testCompletedwithTestGroup() {
        assertFalse(msg.isCompleted());
        msg.setIsTest(true);
        msg.completed();
        assertTrue(msg.isCompleted());
    }

    @Test
    public void testSetName() {
        msg.setName(TEST_NAME);
        assertTrue(msg.getName().equals(TEST_NAME));
    }

    @Test
    public void testSetGroup() {
        msg.setGroup(TEST_GROUP);
        assertTrue(msg.getGroup().equals(TEST_GROUP));
    }
}
