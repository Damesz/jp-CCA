package hu.zsolt.damu.jpmorgan.cca.test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public abstract class ResourceScheduler extends Scheduler {

    private ResourceManager resourceManager;
    private Queue<Message> messageQueue;
    private Thread thread;
    private Gateway gateway;

    private List<MessageGroup> cancelledGroup;

    public ResourceScheduler(ResourceManager resourceManager, Gateway gateway) {
        this.resourceManager = resourceManager;
        this.gateway = gateway;
        messageQueue = new ConcurrentLinkedDeque<Message>();
        cancelledGroup = new ArrayList<MessageGroup>();
    }

    @Override
    public synchronized void addMessage(Message msg) {
        synchronized (messageQueue) {
            messageQueue.add(msg);
            notify();
        }
    }

    @Override
    public void start() {
        if(thread == null) {
            thread = new Thread(new SchedulerThread(resourceManager, messageQueue));
        }
        if (!thread.isAlive())
            thread.start();
    }

    public synchronized void waitForMessages() throws InterruptedException {
        wait();
    }

    @Override
    public synchronized void cancelGroup(MessageGroup group) {
        cancelledGroup.add(group);
    }

    private class SchedulerThread implements Runnable {
        private  ResourceManager resourceManager;
        private Queue<Message> messageQueue;
        private boolean run = true;

        public SchedulerThread(ResourceManager resourceManager, Queue<Message> messageQueue) {
            this.resourceManager = resourceManager;
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            while (run) {
                try {
                    System.out.println("Waiting for messages...");
                    waitForMessages();
                    synchronized (messageQueue) {
                        while (!messageQueue.isEmpty()){
                            while (!resourceManager.hasAvailableResources()) {
                                resourceManager.waitForResources();
                                if (resourceManager.hasAvailableResources()) {
                                    break;
                                }
                            }

                            Message msg = messagePrioritisingAlgorithm(messageQueue);

                            if (msg != null && !cancelledGroup.contains(msg.getGroup())) {
                                System.out.println("Start messages process...");
                                resourceManager.processMessage(msg, gateway);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public Message messagePrioritisingAlgorithm(Queue<Message> messages) {
            Message highPriorityMessage = messages.poll();

            for (Message msg : messages) {
                if(highPriorityMessage.getGroup().getPriority() < msg.getGroup().getPriority()) {
                    messages.add(highPriorityMessage);
                    highPriorityMessage = msg;
                    messages.remove(highPriorityMessage);
                }
            }

            return highPriorityMessage;
        }
    }
}
