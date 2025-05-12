import java.io.*;
import java.util.*;
import java.util.regex.*;
// Abstract User class
public abstract class User {
    private String userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String contactInfo;

    public User(String userId, String username, String password, String name, String email, String contactInfo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public boolean checkPassword(String inputPassword) { return this.password.equals(inputPassword); }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContactInfo() { return contactInfo; }

    public void updateProfile(String name, String email, String contactInfo) {
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
        System.out.println("Profile updated successfully.");
    }

    public abstract void displayMenu();
}
