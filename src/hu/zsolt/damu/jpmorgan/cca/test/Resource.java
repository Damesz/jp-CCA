package hu.zsolt.damu.jpmorgan.cca.test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public abstract class Resource {

    protected String name;

    public Resource(String name) {
        this.name = name;
    }

    public abstract void start(Message msg, Gateway gateway, CountDownLatch end) throws  InterruptedException;

    public abstract boolean isAvailable();
    public abstract int getSpeed();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                '}';
    }
}
