import java.util.*;

public class AdminStaff extends User {
    private String role;  // Role field for AdminStaff

    public AdminStaff(String userId, String username, String password, String name, String email, String contactInfo) {
        super(userId, username, password, name, email, contactInfo);
        this.role = "Admin"; // Default role
    }

    // Getter and Setter for Role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Register a student
    public Student registerStudent(String studentId, String username, String password, String name, String email,
                                   String contact, String admissionDate, String academicStatus) {
        return new Student(studentId, username, password, name, email, contact, studentId, admissionDate, academicStatus);
    }

    // Create a new course
    public Course createCourse(String courseId, String courseName, String description, int creditHours, int capacity) {
        return new Course(courseId, courseName, description, creditHours, capacity);
    }

    // Assign a faculty member to a course
    public void assignFacultyToCourse(Faculty faculty, Course course) {
        // Ensure that the faculty can be assigned to the course
        if (faculty != null && course != null) {
            // Logic to assign faculty to the course
            System.out.println(faculty.getName() + " has been assigned to " + course.getCourseName());
        } else {
            System.out.println("Invalid faculty or course.");
        }
    }


    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void logout() {
        System.out.println("Admin staff logged out.");
    }

    @Override
    public void updateProfile(String name, String email, String contactInfo) {
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
        System.out.println("Admin profile updated.");
    }
}
