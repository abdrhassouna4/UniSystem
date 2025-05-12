import java.util.*;

public class Student extends User {
    private String studentId;
    private String admissionDate;
    private String academicStatus;
    private List<Course> enrolledCourses;  // Now we store the list of courses directly

    // Constructor to initialize student with common properties
    public Student(String username, String password, String name, String email, String contactInfo,
                   String studentId, String admissionDate, String academicStatus) {
        super(username, password, name, email, contactInfo);
        this.studentId = studentId;
        this.admissionDate = admissionDate;
        this.academicStatus = academicStatus;
        this.enrolledCourses = new ArrayList<>();
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
        enrolledCourses.remove(course);
        course.removeStudent(this);  // Remove the student from the course
        System.out.println("Dropped course: " + course.getTitle());
    }

    // View grades for all enrolled courses
    public void viewGrades() {
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
            // This can be extended if grades are stored in an Enrollment object
            for (Course c : enrolledCourses) {
                System.out.println("Course: " + c.getTitle() + ", Grade: Not yet assigned.");
            }
        }
    }

    // Calculate GPA based on grades and course credits
    public double calculateGPA() {
        if (enrolledCourses.isEmpty()) {
            System.out.println("No courses enrolled.");
            return 0;
        }

        double totalPoints = 0;
        int totalCredits = 0;

        for (Course c : enrolledCourses) {
            // Dummy grade points for now. You can replace this with actual grade points logic.
            double gradePoints = 4.0;  // Assuming a GPA scale of A = 4.0
            int credits = c.getCreditHours();
            totalPoints += gradePoints * credits;
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            return 0;
        }

        return totalPoints / totalCredits;  // GPA = Total Grade Points / Total Credits
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
