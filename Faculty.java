import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Faculty extends User {
    private String facultyId;

    public Faculty(String u, String un, String p, String n, String e, String c, String id) {
        super(u, un, p, n, e, c);
        this.facultyId = id;
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n========== Faculty Menu ==========");
            System.out.println("1. View My Courses");
            System.out.println("2. Manage Enrolled Students");
            System.out.println("3. Enter or Update Student Grades");
            System.out.println("4. Update Profile");
            System.out.println("5. Logout");

            System.out.print("Enter your choice (1-5): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\nYour Assigned Courses:");
                        System.out.println("- CS101: Introduction to Programming");
                        System.out.println("- CS201: Data Structures");
                        break;
                    case 2:
                        System.out.print("\nEnter course code to view enrolled students: ");
                        String courseCode = scanner.nextLine();
                        System.out.println("Students enrolled in " + courseCode + ":");
                        System.out.println("- Student A (ID: S001)");
                        System.out.println("- Student B (ID: S002)");
                        break;
                    case 3:
                        System.out.println("\nUpdate Student Grade:");
                        System.out.print("Enter student ID: ");
                        String studentId = scanner.nextLine();
                        System.out.print("Enter course code: ");
                        String course = scanner.nextLine();
                        System.out.print("Enter grade: ");
                        String grade = scanner.nextLine();
                        System.out.println("Grade updated: " + grade + " for student " + studentId + " in " + course);
                        break;
                    case 4:
                        System.out.println("\nUpdate Profile:");
                        System.out.print("Enter new name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter new contact info: ");
                        String contact = scanner.nextLine();
                        updateProfile(name, email, contact);
                        break;
                    case 5:
                        continueMenu = false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between 1-5.");
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
