import java.io.*;
import java.util.*;
import java.util.regex.*;
// Student class
class Student extends User {
    private String studentId;
    private String admissionDate;
    private String academicStatus;
    private Enrollment[] enrolledCourses; 
    private int enrolledCourseCount;

    public Student(String userId, String username, String password, String name, String email, String contactInfo,
                   String studentId, String admissionDate, String academicStatus) {
        super(userId, username, password, name, email, contactInfo);
        this.studentId = studentId;
        this.admissionDate = admissionDate;
        this.academicStatus = academicStatus;
        this.enrolledCourses = new Enrollment[8]; 
        this.enrolledCourseCount = 0;
    }

    public void registerForCourse(Course course) {
        if (enrolledCourseCount >= 8) {
            System.out.println("Maximum courses reached.");
            return;
        }

        if (course.getAvailableSeats() > 0) {
            if (course.isPrerequisiteSatisfied(this)) {
                Enrollment enrollment = new Enrollment(this, course);
                enrolledCourses[enrolledCourseCount++] = enrollment;
                course.addStudent(this);
                System.out.println("Course registered successfully.");
            } else {
                System.out.println("Prerequisites not satisfied.");
            }
        } else {
            System.out.println("No available seats.");
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public void dropCourse(Course course) {
        boolean found = false;
        for (int i = 0; i < enrolledCourseCount; i++) {
            if (enrolledCourses[i].getCourse().equals(course)) {
                // Shift remaining elements
                for (int j = i; j < enrolledCourseCount - 1; j++) {
                    enrolledCourses[j] = enrolledCourses[j + 1];
                }
                enrolledCourses[--enrolledCourseCount] = null;
                course.removeStudent(this.getStudentId());
                System.out.println("Course dropped successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("You are not enrolled in this course.");
        }
    }

    public void viewGrades() {
        System.out.println("\n=== Your Grades ===");
        for (int i = 0; i < enrolledCourseCount; i++) {
            Enrollment e = enrolledCourses[i];
            System.out.println("Course: " + e.getCourse().getTitle()
                    + ", Grade: " + (e.getGrade() != null ? e.getGrade() : "Not graded yet"));
        }
    }

    public double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;
        for (int i = 0; i < enrolledCourseCount; i++) {
            Enrollment e = enrolledCourses[i];
            if (e.getGrade() != null) {
                totalPoints += e.getGradePoint() * e.getCourse().getCreditHours();
                totalCredits += e.getCourse().getCreditHours();
            }
        }
        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }

    public Enrollment[] getEnrolledCourses() {
        return enrolledCourses;
    }

    public int getCourseCount() {
        return enrolledCourseCount;
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n========== Student Menu ==========");
            System.out.println("1. Register for Course");
            System.out.println("2. Drop Course");
            System.out.println("3. View Grades");
            System.out.println("4. View GPA");
            System.out.println("5. Update Profile");
            System.out.println("6. Logout");
            System.out.print("Enter your choice (1-6): ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\nRegistering for a course...");
                        // Implementation would go here
                        break;
                    case 2:
                        System.out.println("\nDropping a course...");
                        // Implementation would go here
                        break;
                    case 3:
                        viewGrades();
                        break;
                    case 4:
                        System.out.printf("\nYour GPA: %.2f\n", calculateGPA());
                        break;
                    case 5:
                        System.out.println("\nUpdate Profile:");
                        System.out.print("Enter new name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter new contact info: ");
                        String contact = scanner.nextLine();
                        updateProfile(name, email, contact);
                        break;
                    case 6:
                        continueMenu = false;
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between 1-6.");
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
