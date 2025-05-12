public abstract class User {
    protected static int userIdCounter = 1000; // Start user IDs from 1000
    protected int userId;
    protected String username;
    protected String password;
    protected String name;
    protected String email;
    protected String contactInfo;

    // Constructor to initialize user with common properties
    public User(String username, String password, String name, String email, String contactInfo) {
        this.userId = userIdCounter++;  // Assign unique ID and increment counter
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
        System.out.println("Your User ID is: " + this.getUserId());
    }

    // Login method to check username and password
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    // Logout method
    public void logout() {
        System.out.println(name + " logged out.");
    }

    // Update user profile information
    public void updateProfile(String name, String email, String contactInfo) {
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
        System.out.println("Profile updated successfully.");
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        if (password.length() >= 6) {
            this.password = password;
        } else {
            System.out.println("Password must be at least 6 characters long.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Abstract method to show the menu (to be implemented by subclasses)
    public abstract void showMenu();
}
