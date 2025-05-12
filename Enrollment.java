import java.util.Date;

public class Enrollment {
    private Student student;
    private Course course;
    private Date enrollmentDate;
    private String status;  // "In Progress", "Completed", etc.
    private String grade;   // Grade assigned to the student

    // Constructor for the enrollment
    public Enrollment(Student student, Course course, Date enrollmentDate, String status) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        this.grade = null;  // Initially no grade is assigned
    }

    // ✅ Method to assign a grade to the student for this course
    public void assignGrade(String grade) {
        this.grade = grade;
        this.status = "Completed";  // Once grade is assigned, the course is marked as completed
        System.out.println("Grade " + grade + " assigned to student " + student.getName() + " for course " + course.getTitle());
    }

    // ✅ Method to get the status of the enrollment
    public String getStatus() {
        return status;
    }

    // ✅ Method to withdraw from the course
    public void withdraw() {
        this.status = "Withdrawn";
        System.out.println("Student " + student.getName() + " has withdrawn from course: " + course.getTitle());
    }

    // Getter method for grade
    public String getGrade() {
        return grade;
    }

    // Getter method for student
    public Student getStudent() {
        return student;
    }

    // Getter method for course
    public Course getCourse() {
        return course;
    }

    // Method to get the grade point (assuming a grading scale exists)
    public double getGradePoint() {
        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;  // If no grade is assigned, return 0.0
        }
    }
}
