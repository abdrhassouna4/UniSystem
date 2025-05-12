import java.util.*;

public class Course {
    private String courseId;
    private String title;
    private String department;
    private int creditHours;  // Represents the number of credit hours for the course
    private Faculty instructor;
    private List<Student> enrolledStudents;
    private List<Course> prerequisiteCourses;

    // Constructor to initialize course properties
    public Course(String courseId, String title, String department, int creditHours) {
        this.courseId = courseId;
        this.title = title;
        this.department = department;
        this.creditHours = creditHours;
        this.enrolledStudents = new ArrayList<>();
        this.prerequisiteCourses = new ArrayList<>();
    }
    public String getCourseId() {
        return courseId;
    }
    // Getter for course title
    public String getTitle() {
        return title;
    }

    // Getter for credit hours
    public int getCreditHours() {
        return creditHours;
    }

    // Method to add student to the course
    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }

    // Method to remove student from the course
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    // Method to get available seats in the course
    public int getAvailableSeats() {
        return 30 - enrolledStudents.size();  // Assume max of 30 students per course
    }

    // Method to set the course instructor
    public void setInstructor(Faculty faculty) {
        this.instructor = faculty;
    }

    // Getter for the instructor
    public Faculty getInstructor() {
        return instructor;
    }

    // Method to check if prerequisites are satisfied
    public boolean isPrerequisiteSatisfied(Course prerequisiteCourse) {
        return prerequisiteCourses.contains(prerequisiteCourse);
    }

    // Method to add prerequisite course for the current course
    public void addPrerequisiteCourse(Course course) {
        prerequisiteCourses.add(course);
    }

    // Method to get list of enrolled students
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Method to get list of prerequisite courses
    public List<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    // Method to display course details
    public void displayCourseDetails() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Title: " + title);
        System.out.println("Department: " + department);
        System.out.println("Credit Hours: " + creditHours);
        System.out.println("Instructor: " + (instructor != null ? instructor.getName() : "Not Assigned"));
        System.out.println("Enrolled Students: " + enrolledStudents.size() + "/30");
    }

    // Method to get total number of enrolled students
    public int getEnrollmentCount() {
        return enrolledStudents.size();
    }
}
