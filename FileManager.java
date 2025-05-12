import java.io.*;
import java.util.*;

public class FileManager {
    public static void saveUsers(List<User> users, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (User u : users) {
                String role = u.getClass().getSimpleName().toLowerCase();
                String id = "";

                if (u instanceof Student) id = ((Student) u).getStudentId();
                else if (u instanceof Faculty) id = ((Faculty) u).getFacultyId();
                else if (u instanceof AdminStaff) id = ((AdminStaff) u).getStaffId();
                else if (u instanceof SystemAdmin) id = ((SystemAdmin) u).getAdminId();

                bw.write(role + "," + u.username + "," + u.password + "," + u.name + "," + u.email + "," + u.contactInfo + "," + id);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public static List<User> loadUsers(String filename) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String role = parts[0];
                    String username = parts[1];
                    String password = parts[2];
                    String name = parts[3];
                    String email = parts[4];
                    String contact = parts[5];
                    String id = parts[6];

                    switch (role) {
                        case "student":
                            users.add(new Student(username, password, name, email, contact, id, "2023-09-01", "Active"));
                            break;
                        case "faculty":
                            // Add the expertise parameter when creating a new Faculty object
                            users.add(new Faculty(username, password, name, email, contact, id, "CS", "Computer Science"));
                            break;

                        case "adminstaff":
                            users.add(new AdminStaff(username, password, name, email, contact, id, "Admin", "Registrar"));
                            break;
                        case "systemadmin":
                            users.add(new SystemAdmin(username, password, name, email, contact, id, "High"));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }
}
