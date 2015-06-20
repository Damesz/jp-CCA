package hu.zsolt.damu.jpmorgan.cca.test;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public abstract class Message {

    private static final MessageGroup DEFAULT_GROUP = MessageGroup.DEFAULT;
    private static final String DEFAULT_NAME = "unknown";

    private MessageGroup group;
    private String name;
    private boolean isTest = false;

    public Message() {
        name = DEFAULT_NAME;
        group = DEFAULT_GROUP;
    }

    public Message(MessageGroup group) {
        this.group = group;
        name = DEFAULT_NAME;
    }

    public Message(MessageGroup group, String name) {
        this.group = group;
        this.name = name;
    }

    public Message(MessageGroup group, String name, String data) {
        this.group = group;
        this.name = name;
    }

    public abstract void completed();
    public abstract boolean isCompleted();

    public MessageGroup getGroup() {
        return group;
    }

    public void setGroup(MessageGroup group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsTest(boolean isTest) { this.isTest = isTest; }

    public String sentMessage() {
        return name + "message sent successfully";
    }

    @Override
    public String toString() {
        return "Message{" +
                "group=" + group.getName() +
                ", name='" + name + '\'' +
                '}';
    }
}
