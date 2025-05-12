import java.util.*;
import java.io.*;

public class Main {
    private static final String FILENAME = "users.txt";
    private static List<User> users = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        users = FileManager.loadUsers(FILENAME);

        // Sample courses
        courses.add(new Course("CS101", "Intro to CS", "Computer", 50));
        courses.add(new Course("MATH201", "Calculus I", "Mathetmatics", 40));
        courses.add(new Course("PHY111", "Physics I", "Science", 35));

        System.out.println("=== Welcome to Alexandria University Management System ===");

        boolean running = true;
        while (running) {
            System.out.print("\n1. Sign Up\n2. Login\n3. Exit\nChoose option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    signUp(scanner);
                    FileManager.saveUsers(users, FILENAME);
                    break;
                case "2":
                    login(scanner);
                    break;
                case "3":
                    running = false;
                    FileManager.saveUsers(users, FILENAME);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Choose role (student/faculty/adminstaff/systemadmin): ");
        String role = scanner.nextLine().toLowerCase();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        String password;
        while (true) {
            System.out.print("Password (min 6 characters, must include a number): ");
            password = scanner.nextLine();
            if (password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$")) break;
            System.out.println("Invalid password format.");
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contact Info: ");
        String contact = scanner.nextLine();
        System.out.print("ID (e.g., student ID/faculty ID): ");
        String id = scanner.nextLine();

        switch (role) {
            case "student":
                users.add(new Student(username, password, name, email, contact, id, "2023-09-01", "Active"));
                break;
            case "faculty":
                // Add expertise when creating a new Faculty object, e.g., "Computer Science"
                users.add(new Faculty(username, password, name, email, contact, id, "CS", "Computer Science"));
                break;

            case "adminstaff":
                users.add(new AdminStaff(username, password, name, email, contact, id, "Admin", "Registrar"));
                break;
            case "systemadmin":
                users.add(new SystemAdmin(username, password, name, email, contact, id, "High"));
                break;
            default:
                System.out.println("Invalid role.");
        }

        System.out.println("Sign-up successful!");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.login(username, password)) {
                System.out.println("Login successful!");

                boolean loggedIn = true;
                while (loggedIn) {
                    user.showMenu();
                    System.out.print("Choose an option: ");
                    String choice = scanner.nextLine();

                    if (user instanceof Student) {
                        Student student = (Student) user;
                        switch (choice) {
                            case "1":
                                // List all available courses
                                System.out.println("Available courses:");
                                for (Course c : courses) {
                                    System.out.println(c.getCourseId() + ": " + c.getTitle());
                                }
                                System.out.print("Enter course ID to register: ");
                                String cid = scanner.nextLine();
                                Course selected = findCourseById(cid);
                                if (selected != null) {
                                    student.registerForCourse(selected);
                                    System.out.println("Registered successfully!");
                                } else {
                                    System.out.println("Course not found.");
                                }
                                break;
                            case "2":
                                System.out.print("Enter course ID to drop: ");
                                String dropId = scanner.nextLine();
                                Course dropCourse = findCourseById(dropId);
                                if (dropCourse != null) {
                                    student.dropCourse(dropCourse);
                                    System.out.println("Dropped successfully.");
                                } else {
                                    System.out.println("Course not found.");
                                }
                                break;
                            case "3":
                                student.viewGrades();
                                break;
                            case "4":
                                double gpa = student.calculateGPA();
                                System.out.println("GPA: " + gpa);
                                break;
                            case "5":
                                loggedIn = false;
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    } else {
                        System.out.println("Dashboard for this role not implemented yet.");
                        loggedIn = false;
                    }
                }

                return;
            }
        }

        System.out.println("Invalid credentials.");
    }

    private static Course findCourseById(String courseId) {
        for (Course c : courses) {
            if (c.getCourseId().equalsIgnoreCase(courseId)) {
                return c;
            }
        }
        return null;
    }
}
