package hu.zsolt.damu.jpmorgan.cca.test.impl;

import hu.zsolt.damu.jpmorgan.cca.test.Gateway;
import hu.zsolt.damu.jpmorgan.cca.test.Message;
import hu.zsolt.damu.jpmorgan.cca.test.Resource;
import hu.zsolt.damu.jpmorgan.cca.test.ResourceManager;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class ResourceManagerImpl extends ResourceManager {

    public ResourceManagerImpl() {
        super();
    }

    public ResourceManagerImpl(List<Resource> resources) {
        super(resources);
    }

    @Override
    public void addResource(String name) {
        Resource resource = new ResourceImpl(name);
        addResource(resource);
    }

    @Override
    public void processMessage(Message msg, Gateway gateway) throws InterruptedException {
        Resource resource = getFastestAvailableResource();
        if(resource != null) {
            processMessage(resource, msg, gateway);
        }
    }

    @Override
    public void processMessage(Resource res, Message msg, Gateway gateway) throws InterruptedException {
        CountDownLatch end = new CountDownLatch(1);
        res.start(msg, gateway, end);

        Thread checkerThread = new Thread(new CheckerThread());
        checkerThread.start();
        updateAvailableResource();
        System.out.println("Sent: " + msg);
    }

    private class CheckerThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                updateAvailableResource();
            }
        }
    }
}
