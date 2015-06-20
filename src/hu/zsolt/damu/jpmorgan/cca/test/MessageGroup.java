package hu.zsolt.damu.jpmorgan.cca.test;

/**
 * Created by TamasZsolt on 20/06/2015.
 */
public enum MessageGroup {
    DEFAULT(0),
    GROUP_ONE(2),
    GROUP_TWO(3),
    GROUP_THREE(1),
    GROUP_TEST(-1);

    private int priority;

    MessageGroup(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "MessageGroup{" +
                "priority=" + priority +
                '}';
    }

    public String getName() {
        return name().toLowerCase();
    }
}
