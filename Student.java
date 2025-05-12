import java.util.*;

public class Student extends User {
    private String studentId;
    private String admissionDate;
    private String academicStatus;
    private List<Course> enrolledCourses;
    private List<Course> completedCourses;  // New list to track completed courses

    // Constructor to initialize student with common properties
    public Student(String username, String password, String name, String email, String contactInfo,
                   String studentId, String admissionDate, String academicStatus) {
        super(username, password, name, email, contactInfo);
        this.studentId = studentId;
        this.admissionDate = admissionDate;
        this.academicStatus = academicStatus;
        this.enrolledCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
    }

    // Getter methods
    public String getStudentId() {
        return studentId;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public List<Course> getCompletedCourses() {
        return completedCourses;
    }

    // Register a course if there are available seats
    public void registerForCourse(Course course) {
        if (course.getAvailableSeats() > 0) {
            enrolledCourses.add(course);
            course.addStudent(this);  // Enroll the student in the course
            System.out.println("Registered for course: " + course.getTitle());
        } else {
            System.out.println("Course is full.");
        }
    }

    // Drop a course
    public void dropCourse(Course course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
            course.removeStudent(this);
            System.out.println("Dropped course: " + course.getTitle());
        } else {
            System.out.println("You are not enrolled in this course.");
        }
    }

    // Mark a course as completed
    public void completeCourse(Course course) {
        if (enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
            completedCourses.add(course);
            System.out.println("Course " + course.getTitle() + " marked as completed.");
        } else {
            System.out.println("Cannot complete a course you are not enrolled in.");
        }
    }

    // View grades for all enrolled courses
    public void viewGrades() {
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
            for (Course c : enrolledCourses) {
                System.out.println("Course: " + c.getTitle() + ", Grade: Not yet assigned.");
            }
        }
    }

    // Calculate GPA based on dummy grade points and course credits
    public double calculateGPA() {
        if (completedCourses.isEmpty()) {
            System.out.println("No completed courses.");
            return 0;
        }

        double totalPoints = 0;
        int totalCredits = 0;

        for (Course c : completedCourses) {
            double gradePoints = 4.0;  // Dummy value (e.g., assume all A's for now)
            int credits = c.getCreditHours();
            totalPoints += gradePoints * credits;
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            return 0;
        }

        return totalPoints / totalCredits;
    }

    // Check if a course has been completed
    public boolean hasCompletedCourse(Course course) {
        return completedCourses.contains(course);
    }

    @Override
    public void showMenu() {
        System.out.println("Welcome, Student: " + name);
        System.out.println("1. Register for a Course");
        System.out.println("2. Drop a Course");
        System.out.println("3. View Grades");
        System.out.println("4. Calculate GPA");
        System.out.println("5. Logout");
    }

    public String getName() {
        return name;
    }
}
