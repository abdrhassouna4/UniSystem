import java.io.*;
import java.util.*;
import java.util.regex.*;
public class AdminStaff extends User {
    private String staffId, department, role;
    public AdminStaff(String u, String un, String p, String n, String e, String c, String id, String dept, String role) {
        super(u, un, p, n, e, c);
        this.staffId = id;
        this.department = dept;
        this.role = role;
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n========== Admin Staff Menu ==========");
            System.out.println("1. View Department Info");
            System.out.println("2. Assign Student to Department");
            System.out.println("3. Update Profile");
            System.out.println("4. Logout");

            System.out.print("Enter your choice (1-4): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\nDepartment Information:");
                        System.out.println("Department: " + department);
                        System.out.println("Role: " + role);
                        break;
                    case 2:
                        System.out.print("\nEnter student ID to assign: ");
                        String studentId = scanner.nextLine();
                        System.out.println("Student " + studentId + " assigned to " + department);
                        break;
                    case 3:
                        System.out.println("\nUpdate Profile:");
                        System.out.print("Enter new name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter new contact info: ");
                        String contact = scanner.nextLine();
                        updateProfile(name, email, contact);
                        break;
                    case 4:
                        continueMenu = false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between 1-4.");
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
}
