package fit.se.thtuan06.beans.javabased;

public class User {
    private String userName;
    private String password;
    private Group group;

    // Constructors
    public User() {
    }

    public User(String userName, String password, Group group) {
        this.userName = userName;
        this.password = password;
        this.group = group;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + ", password=" + password + ", group=" + group + "]";
    }
}