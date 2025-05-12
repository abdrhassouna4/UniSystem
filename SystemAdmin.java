import java.util.ArrayList;
import java.util.List;

public class SystemAdmin extends User {
    private String adminId;
    private String securityLevel;
    private List<User> managedUsers;

    // Constructor
    public SystemAdmin(String username, String password, String name, String email, String contactInfo, String adminId, String securityLevel) {
        super(username, password, name, email, contactInfo);
        this.adminId = adminId;
        this.securityLevel = securityLevel;
        this.managedUsers = new ArrayList<>();
    }

    // Create a new user and add to managed users list
    public void createUser(User newUser) {
        managedUsers.add(newUser);
        System.out.println("User " + newUser.getUsername() + " created successfully.");
    }

    // Modify system settings (placeholder)
    public void modifySystemSettings(String settingName, String value) {
        System.out.println("System setting '" + settingName + "' updated to: " + value);
    }

    // Backup data (placeholder)
    public void backupData() {
        System.out.println("System data has been backed up successfully.");
    }

    // Manage user permissions (placeholder)
    public void managePermissions(User user, String permissionLevel) {
        System.out.println("Permissions for user " + user.getUsername() + " set to: " + permissionLevel);
    }

    // Display admin dashboard menu
    @Override
    public void showMenu() {
        System.out.println("\n=== System Admin Dashboard ===");
        System.out.println("1. Create New User");
        System.out.println("2. Modify System Settings");
        System.out.println("3. Backup System Data");
        System.out.println("4. Manage User Permissions");
        System.out.println("5. Update Profile");
        System.out.println("6. Logout");
        // Add switch-case logic in main app based on choice
    }

    // Getters and Setters
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public List<User> getManagedUsers() {
        return managedUsers;
    }

    public void setManagedUsers(List<User> managedUsers) {
        this.managedUsers = managedUsers;
    }
}
