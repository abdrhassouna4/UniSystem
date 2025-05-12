import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n=== Welcome to the User Portal ===");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    loggedInUser = LoginSystem.login(email, password);
                    if (loggedInUser == null) {
                        LoginSystem.showLoginError();
                    }
                    break;

                case "2":
                    LoginSystem.signUp();
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Once logged in, show the user's menu
        loggedInUser.displayMenu();
    }
}
