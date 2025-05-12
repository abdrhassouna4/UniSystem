import java.io.*;
import java.util.*;
import java.util.regex.*;
class LoginSystem {
    public static User login(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length < 7) continue;

                String fileEmail = data[4];
                String filePassword = data[2];

                if (fileEmail.equalsIgnoreCase(email) && filePassword.equals(password)) {
                    String role = data[6];

                    if (role.equals("Student") && data.length >= 10) {
                        return new Student(data[0], data[1], data[2], data[3], data[4], data[5],
                                           data[7], data[8], data[9]);
                    } else if (role.equals("Instructor") && data.length >= 8) {
                        return new Faculty(data[0], data[1], data[2], data[3], data[4], data[5],
                                           data[7]);
                    } else if (role.equals("AdminStaff") && data.length >= 10) {
                        return new AdminStaff(data[0], data[1], data[2], data[3], data[4], data[5],
                                              data[7], data[8], data[9]);
                    } else if (role.equals("SystemAdmin") && data.length >= 9) {
                        return new SystemAdmin(data[0], data[1], data[2], data[3], data[4], data[5],
                                               data[7], data[8]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        return null;
    }

    public static void showLoginError() {
        System.out.println("Invalid email or password. Please try again.");
    }

    public static void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== Sign Up ===");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter contact info: ");
        String contact = scanner.nextLine();

        System.out.print("Enter role (Student / Instructor / AdminStaff / SystemAdmin): ");
        String role = scanner.nextLine();

        if (emailExists(email)) {
            System.out.println("An account with this email already exists.");
            return;
        }

        String userId = UUID.randomUUID().toString(); // Unique ID

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(userId).append(",").append(username).append(",").append(password).append(",")
              .append(name).append(",").append(email).append(",").append(contact).append(",")
              .append(role);

            // Append extra fields based on role
            if (role.equalsIgnoreCase("Student")) {
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();
                System.out.print("Enter admission date: ");
                String admissionDate = scanner.nextLine();
                System.out.print("Enter academic status: ");
                String status = scanner.nextLine();
                sb.append(",").append(studentId).append(",").append(admissionDate).append(",").append(status);
            } else if (role.equalsIgnoreCase("Instructor")) {
                System.out.print("Enter faculty ID: ");
                String facultyId = scanner.nextLine();
                sb.append(",").append(facultyId);
            } else if (role.equalsIgnoreCase("AdminStaff")) {
                System.out.print("Enter staff ID: ");
                String staffId = scanner.nextLine();
                System.out.print("Enter department: ");
                String dept = scanner.nextLine();
                System.out.print("Enter role in department: ");
                String depRole = scanner.nextLine();
                sb.append(",").append(staffId).append(",").append(dept).append(",").append(depRole);
            } else if (role.equalsIgnoreCase("SystemAdmin")) {
                System.out.print("Enter admin ID: ");
                String adminId = scanner.nextLine();
                System.out.print("Enter security level: ");
                String security = scanner.nextLine();
                sb.append(",").append(adminId).append(",").append(security);
            } else {
                System.out.println("Invalid role. Sign-up failed.");
                return;
            }

            writer.write(sb.toString());
            writer.newLine();
            System.out.println("Sign-up successful!");
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    private static boolean emailExists(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5 && data[4].equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error checking email: " + e.getMessage());
        }
        return false;
    }
}
