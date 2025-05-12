import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Course {
    private String courseCode;
    private String courseName;
    private String facultyId;
    private int creditHours;
    private String description;
    private String schedule;
    private String[] enrolledStudentIds;
    private int enrolledCount;
    private final int MAXENROLLMENTS = 100;

    public Course(String courseCode, String courseName, String facultyId, int creditHours, String description, String schedule) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.facultyId = facultyId;
        this.creditHours = creditHours;
        this.description = description;
        this.schedule = schedule;
        this.enrolledStudentIds = new String[MAXENROLLMENTS];
        this.enrolledCount = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public String getTitle() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean enrollStudent(String studentId) {
        if (enrolledCount < MAXENROLLMENTS) {
            enrolledStudentIds[enrolledCount] = studentId;
            enrolledCount++;
            return true;
        }
        return false;
    }

    public boolean removeStudent(String studentId) {
        for (int i = 0; i < enrolledCount; i++) {
            if (enrolledStudentIds[i].equals(studentId)) {
                for (int j = i; j < enrolledCount - 1; j++) {
                    enrolledStudentIds[j] = enrolledStudentIds[j + 1];
                }
                enrolledStudentIds[enrolledCount - 1] = null;
                enrolledCount--;
                return true;
            }
        }
        return false;
    }

    public void displayCourseInfo() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Name: " + courseName);
        System.out.println("Faculty ID: " + facultyId);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
    }

    public void displayEnrolledStudents() {
        System.out.println("Enrolled students in " + courseCode + ":");
        for (int i = 0; i < enrolledCount; i++) {
            System.out.println((i + 1) + ". Student ID: " + enrolledStudentIds[i]);
        }
    }

    public boolean isStudentEnrolled(String studentId) {
        for (int i = 0; i < enrolledCount; i++) {
            if (enrolledStudentIds[i].equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    public int getAvailableSeats() {
        return MAXENROLLMENTS - enrolledCount;
    }

    public boolean isPrerequisiteSatisfied(Student student) {
        // Simplified - in real implementation would check actual prerequisites
        return true;
    }

    public void addStudent(Student student) {
        if (enrollStudent(student.getStudentId())) {
            System.out.println("Student enrolled successfully");
        } else {
            System.out.println("Failed to enroll student");
        }
    }
}
