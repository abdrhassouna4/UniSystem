import java.util.*;

// Abstract User class
abstract class User {
    protected String userId;
    protected String username;
    protected String password;
    protected String name;
    protected String email;
    protected String contactInfo;

    public User(String userId, String username, String password, String name, String email, String contactInfo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
    }

    // Abstract methods to be implemented by subclasses
    public abstract boolean login(String username, String password);
    public abstract void logout();
    public abstract void updateProfile(String name, String email, String contactInfo);

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getPassword() {
        return password;
    }
}
