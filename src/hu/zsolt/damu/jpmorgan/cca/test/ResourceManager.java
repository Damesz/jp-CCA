package hu.zsolt.damu.jpmorgan.cca.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public abstract class ResourceManager {
    private List<Resource> resources;

    public ResourceManager() {
        resources = Collections.synchronizedList(new ArrayList<Resource>());
    }

    public ResourceManager(List<Resource> resources) {
        this.resources = resources;
    }

    public abstract void addResource(String name);
    public abstract void processMessage(Message msg, Gateway gateway) throws InterruptedException;
    public abstract void processMessage(Resource res, Message msg, Gateway gateway) throws InterruptedException;

    public boolean hasAvailableResources() {
        return getAvailableResources().size() > 0;
    }

    public List<Resource> getAvailableResources() {
        List<Resource> availableResources = new ArrayList<Resource>();

        for (Resource resource : resources) {
            if (resource.isAvailable()) {
                availableResources.add(resource);
            }
        }

        return availableResources;
    }

    public Resource getFastestAvailableResource() {
        List<Resource> availResList = getAvailableResources();
        Resource fastestRes = null;
        if (availResList.size() != 0) {
            fastestRes = availResList.get(0);
            for (Resource availRes: getAvailableResources()) {
                if (fastestRes.getSpeed() > availRes.getSpeed())
                    fastestRes = availRes;
            }
        }

        return fastestRes;
    }

    protected void addResource(Resource res) {
        resources.add(res);
    }

    public synchronized void waitForResources() throws InterruptedException {
        wait();
    }

    public synchronized void updateAvailableResource() {
        if (getAvailableResources().size() > 0) {
            notify();
        }
    }
}
