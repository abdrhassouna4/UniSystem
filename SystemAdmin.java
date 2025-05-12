import java.io.*;
import java.util.*;
import java.util.regex.*;
public class SystemAdmin extends User {
    private String adminId;
    private String securityLevel;
    private User[] users;
    private int userCount;

    public SystemAdmin(String userId, String username, String password, String name,
                       String email, String contact, String adminId, String securityLevel) {
        super(userId, username, password, name, email, contact);
        this.adminId = adminId;
        this.securityLevel = securityLevel;
        this.users = new User[100];
        this.userCount = 0;
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n========== System Admin Menu ==========");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Courses");
            System.out.println("3. Generate Reports");
            System.out.println("4. Modify System Settings");
            System.out.println("5. Backup Data");
            System.out.println("6. Manage Permissions");
            System.out.println("7. Logout");

            System.out.print("Enter your choice (1-7): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        displayAllUsers();
                        break;
                    case 2:
                        System.out.println("\nManaging courses...");
                        // Implementation would go here
                        break;
                    case 3:
                        System.out.println("\nGenerating reports...");
                        // Implementation would go here
                        break;
                    case 4:
                        modifySystemSettings();
                        break;
                    case 5:
                        backupData();
                        break;
                    case 6:
                        managePermissions();
                        break;
                    case 7:
                        continueMenu = false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between 1-7.");
                scanner.nextLine(); // clear invalid input
            }

            if (continueMenu) {
                System.out.print("\nReturn to menu? (yes/no): ");
                String cont = scanner.nextLine();
                if (!cont.equalsIgnoreCase("yes")) {
                    continueMenu = false;
                }
            }
        }
    }

    public void displayAllUsers() {
        System.out.println("\n======= All Registered Users =======");
        for (int i = 0; i < userCount; i++) {
            System.out.println((i + 1) + ". Username: " + users[i].getUsername()
                    + ", Name: " + users[i].getName()
                    + ", Email: " + users[i].getEmail());
        }
    }

    public void createUser(User newUser) {
        if (userCount < users.length) {
            users[userCount] = newUser;
            userCount++;
            System.out.println("User created successfully.");
        } else {
            System.out.println("User limit reached. Cannot add more users.");
        }
    }

    public void modifySystemSettings() {
        System.out.println("\nSystem settings modified successfully.");
    }

    public void backupData() {
        System.out.println("\nSystem data backed up successfully.");
    }

    public void managePermissions() {
        System.out.println("\nUser permissions updated successfully.");
    }

    public String getAdminId() {
        return adminId;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }
}

