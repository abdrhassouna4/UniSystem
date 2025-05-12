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

    // Getter for course ID
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
    public boolean addStudent(Student student) {
        // Check if prerequisites are satisfied before adding the student
        for (Course prerequisite : prerequisiteCourses) {
            if (!student.hasCompletedCourse(prerequisite)) {
                System.out.println("Cannot add student: Prerequisites not satisfied for " + student.getName());
                return false;  // Student cannot be added if prerequisites are not satisfied
            }
        }

        if (getAvailableSeats() > 0) {
            enrolledStudents.add(student);
            System.out.println("Added student " + student.getName() + " to " + title);
            return true;
        } else {
            System.out.println("Course is full. Cannot add student.");
            return false;  // No seats available
        }
    }

    // Method to remove student from the course
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
        System.out.println("Removed student " + student.getName() + " from " + title);
    }

    // Method to get available seats in the course
    public int getAvailableSeats() {
        return 30 - enrolledStudents.size();  // Assume max of 30 students per course
    }

    // Method to set the course instructor
    public void setInstructor(Faculty faculty) {
        this.instructor = faculty;
        System.out.println("Instructor " + faculty.getName() + " has been assigned to " + title);
    }

    // Getter for the instructor
    public Faculty getInstructor() {
        return instructor;
    }

    // Method to check if prerequisites are satisfied for the course
    public boolean isPrerequisiteSatisfied(Course course) {
        return prerequisiteCourses.contains(course);
    }

    // Method to add prerequisite course for the current course
    public void addPrerequisiteCourse(Course course) {
        prerequisiteCourses.add(course);
        System.out.println("Prerequisite " + course.getTitle() + " has been added for " + title);
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

        // Display prerequisites for the course
        if (!prerequisiteCourses.isEmpty()) {
            System.out.print("Prerequisites: ");
            for (Course prereq : prerequisiteCourses) {
                System.out.print(prereq.getTitle() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No prerequisites for this course.");
        }
    }

    // Method to get total number of enrolled students
    public int getEnrollmentCount() {
        return enrolledStudents.size();
    }
}
