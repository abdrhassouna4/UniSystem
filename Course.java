import java.util.*;

public class Course {
    private String courseId;
    private String courseName;
    private String description;
    private int creditHours;
    private int capacity;
    private List<Student> enrolledStudents;
    private Map<Student, String> grades;
    private List<String> prerequisites;
    private Faculty instructor;

    // Constructor
    public Course(String courseId, String courseName, String description, int creditHours, int capacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.creditHours = creditHours;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
        this.grades = new HashMap<>();
        this.prerequisites = new ArrayList<>();
    }

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCreditHours() { return creditHours; }
    public void setCreditHours(int creditHours) { this.creditHours = creditHours; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public List<Student> getEnrolledStudents() { return enrolledStudents; }

    public int getAvailableSeats() { return capacity - enrolledStudents.size(); }

    public void printAvailableCourses(University university) {
        university.printAllCourses();  // Call the university's method to print all courses
    }

    public Faculty getInstructor() { return instructor; }
    public void setInstructor(Faculty instructor) { this.instructor = instructor; }

    // Prerequisites
    public void addPrerequisite(String courseId) {
        prerequisites.add(courseId);
    }

    public boolean isPrerequisiteSatisfied(Student student) {
        for (String pre : prerequisites) {
            String grade = student.getGrade(pre);
            if (grade.equals("N/A") || grade.equals("F")) {
                return false;
            }
        }
        return true;
    }

    // Enrollment
    public boolean enrollStudent(Student student) {
        if (enrolledStudents.contains(student)) return false;
        if (getAvailableSeats() > 0 && isPrerequisiteSatisfied(student)) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    public void dropStudent(Student student) {
        enrolledStudents.remove(student);
        grades.remove(student);
    }

    public void assignGrade(Student student, String grade) {
        if (enrolledStudents.contains(student)) {
            grades.put(student, grade);
            student.receiveGrade(this, grade); // sync with student
        }
    }

    public String getGrade(Student student) {
        return grades.getOrDefault(student, "N/A");
    }

    public void printRoster() {
        System.out.println("Course: " + courseName + " (" + courseId + ")");
        System.out.println("Enrolled Students:");
        for (Student s : enrolledStudents) {
            System.out.println(" - " + s.getName() + " (" + s.getStudentId() + ")");
        }
    }
}
