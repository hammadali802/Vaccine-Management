package hbv.example.model;

public class Relative {

    private int id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;
    private String firstName;
    private String lastName;

    private User user;

    public Relative() {
    }

    public Relative(int userId,  String firstName, String lastName) {
        this.userId = userId;
//        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // You can add additional methods specific to your relative model (e.g., getters/setters for other attributes)
}
