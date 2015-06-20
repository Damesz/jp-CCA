package hu.zsolt.damu.jpmorgan.cca.test.junit;

import hu.zsolt.damu.jpmorgan.cca.test.Gateway;
import hu.zsolt.damu.jpmorgan.cca.test.Message;
import hu.zsolt.damu.jpmorgan.cca.test.Resource;
import hu.zsolt.damu.jpmorgan.cca.test.ResourceManager;
import hu.zsolt.damu.jpmorgan.cca.test.impl.GatewayImpl;
import hu.zsolt.damu.jpmorgan.cca.test.impl.MessageImpl;
import hu.zsolt.damu.jpmorgan.cca.test.impl.ResourceImpl;
import hu.zsolt.damu.jpmorgan.cca.test.impl.ResourceManagerImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.*;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class ReourceManagerImplTest {

    private ResourceManager resourceManager;
    private List<Resource> resources;

    private static final int MAX_WAITING = 3000;
    private static final int RESOURCES_COUNT = 3;
    private static final String TEST_NAME = "Test";

    @Before
    public void setUp() throws Exception {
        resources = new ArrayList<Resource>();
        for (int i = 0; i < RESOURCES_COUNT; i++) {
            Resource resource = new ResourceImpl(i + 1 + " " + TEST_NAME);
            resources.add(resource);
        }

        resourceManager = new ResourceManagerImpl(resources);
    }

    @Test
    public void testGetAvailableResources() throws InterruptedException {
        assertTrue(resourceManager.getAvailableResources().size() == RESOURCES_COUNT);

        Iterator<Resource> it = resourceManager.getAvailableResources().iterator();

        if(it.hasNext()) {
            resourceManager.processMessage(it.next(), new MessageImpl(), new GatewayImpl());
            assertTrue(resourceManager.getAvailableResources().size() == RESOURCES_COUNT - 1);

            Thread.sleep(MAX_WAITING);

            TestCase.assertTrue(resourceManager.getAvailableResources().size() == RESOURCES_COUNT);
        }
    }

    @Test
    public void testStartResource() throws InterruptedException {
        resources = new ArrayList<Resource>();
        Resource resource = new ResourceImpl(TEST_NAME);
        resources.add(resource);
        resourceManager = new ResourceManagerImpl(resources);
        Message msg = new MessageImpl();
        Gateway gateway = new GatewayImpl();

        resourceManager.processMessage(resource, msg, gateway);

        assertTrue(resourceManager.getAvailableResources().size() == 0);
        assertFalse(resourceManager.hasAvailableResources());
        assertFalse(resource.isAvailable());

        Thread.sleep(MAX_WAITING);
        assertTrue(resourceManager.hasAvailableResources());
        assertTrue(msg.isCompleted());
    }

    @Test
    public void testStartFastestResource() throws InterruptedException {
        Resource resource = resourceManager.getFastestAvailableResource();
        resourceManager.processMessage(new MessageImpl(), new GatewayImpl());
        assertTrue(resourceManager.getAvailableResources().size() == RESOURCES_COUNT - 1);
        assertFalse(resource.isAvailable());
    }
}
