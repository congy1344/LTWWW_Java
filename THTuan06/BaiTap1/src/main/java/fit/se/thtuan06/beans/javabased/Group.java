package fit.se.thtuan06.beans.javabased;

public class Group {
    private String groupName;

    // Constructors
    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    // Getters and Setters
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group [groupName=" + groupName + "]";
    }
}