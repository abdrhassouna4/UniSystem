import java.util.*;


public class AdminStaff extends User {
    private String staffId;
    private String department;
    private String role;


    // Constructor to initialize AdminStaff with common properties
    public AdminStaff(String username, String password, String name, String email, String contactInfo,
                      String staffId, String department, String role) {
        super(username, password, name, email, contactInfo);
        this.staffId = staffId;
        this.department = department;
        this.role = role;
    }


    // Getter methods
    public String getStaffId() {
        return staffId;
    }


    public String getDepartment() {
        return department;
    }


    public String getRole() {
        return role;
    }


    // Method to register a student
    public void registerStudent(Student student) {
        // You can add logic to store the student in a database or collection
        System.out.println("Student " + student.getName() + " has been successfully registered.");
    }


    // Method to create a course
    public void createCourse(Course course) {
        // You can add logic to store the course in a course database or collection
        System.out.println("Course " + course.getTitle() + " has been created successfully.");
    }


    // Method to assign a faculty to a course
    public void assignFaculty(Course course, Faculty faculty) {
        // Assigning the faculty to the course (assuming the course has a method setInstructor)
        course.setInstructor(faculty);
        System.out.println("Faculty " + faculty.getName() + " has been assigned to course: " + course.getTitle());
    }


    // Method to generate reports (e.g., enrollment status, course completion, etc.)
    public void generateReports() {
        // Placeholder for generating reports (can be expanded as needed)
        System.out.println("Generating system reports...");
        // Example report can be added here
    }


    @Override
    public void showMenu() {
        System.out.println("Welcome, Admin Staff: " + name);
        System.out.println("1. Register Student");
        System.out.println("2. Create Course");
        System.out.println("3. Assign Faculty");
        System.out.println("4. Generate Reports");
        System.out.println("5. Logout");
    }
}