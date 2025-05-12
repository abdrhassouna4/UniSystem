import java.io.*;
import java.util.*;

public class FileManager {

    private static final String FILE_NAME = "users.txt";

    // Reads all users from the file
    public static List<String> readUsers() {
        List<String> users = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("User file not found. Creating a new one.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create users file: " + e.getMessage());
            }
            return users;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
        return users;
    }

    // Appends a new user to the file
    public static void writeUser(String userLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(userLine);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing user: " + e.getMessage());
        }
    }
}
