package hu.zsolt.damu.jpmorgan.cca.test.impl;

import hu.zsolt.damu.jpmorgan.cca.test.Gateway;
import hu.zsolt.damu.jpmorgan.cca.test.Message;
import hu.zsolt.damu.jpmorgan.cca.test.Resource;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public class ResourceImpl extends Resource {

    private volatile boolean available = true;
    private Random random = new Random();

    private final int SPEED = calculateSpeed();

    public ResourceImpl(String name) {
        super(name);
    }

    @Override
    public void start(Message msg, Gateway gateway, CountDownLatch end) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        Thread thread = null;

        if(thread == null) {
            thread = new Thread(new ResourceThread(msg, gateway, start, end));
        }
        if (!thread.isAlive()) {
            thread.start();
            start.await();
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }

    private int calculateSpeed() {
        return random.nextInt(10 - 1 + 1) + 1;
    }

    private class ResourceThread implements Runnable {
        private final CountDownLatch start;
        private final CountDownLatch end;

        private Message msg;
        private Gateway gateway;

        public ResourceThread(Message msg, Gateway gateway, CountDownLatch resourceStarts, CountDownLatch resourceEnds) {
            this.start = resourceStarts;
            this.end = resourceEnds;
            this.msg = msg;
            this.gateway = gateway;
        }

        @Override
        public void run() {
            available = false;
            start.countDown();

            try {
                Thread.sleep(calculateSleep(1000, 3000));
                if(!msg.isCompleted()) {
                    gateway.send(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                available = true;
                end.countDown();
            }
        }

        private int calculateSleep(int min, int max) {
            return random.nextInt(max - min + 1) + min ;
        }
    }
}
