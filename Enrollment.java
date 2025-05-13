import java.util.*;

class Enrollment {
    private Student student;
    private Course course;
    private String enrollmentDate;
    private String grade;
    private String status;

    public Enrollment(Student student, Course course, String enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = "Enrolled";
    }

    public void assignGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void withdraw() {
        this.status = "Withdrawn";
    }
}

