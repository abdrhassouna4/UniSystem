public class Enrollment {
    private Student student;
    private Course course;
    private Double grade;
    private String enrollmentDate;
    private String status; // "Enrolled", "Withdrawn", "Completed"

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = java.time.LocalDate.now().toString();
        this.grade = null;
        this.status = "Enrolled";
    }

    public Course getCourse() { return course; }
    public Double getGrade() { return grade; }
    public void setGrade(double g) {
        grade = g;
        status = "Completed";
    }

    public boolean passed() { return grade != null && grade >= 50.0; }

    public double getGradePoint() {
        if (grade == null) return 0.0;
        if (grade >= 90) return 4.0;
        else if (grade >= 85) return 3.666;
        else if (grade >= 80) return 3.333;
        else if (grade >= 75) return 3.111;
        else if (grade >= 70) return 2.99;
        else if (grade >= 65) return 2.66;
        else if (grade >= 60) return 2.333;
        else if (grade >= 55) return 2.11;
        else if (grade >= 50) return 2.0;
        else return 0.0;
    }

    public String getStatus() { return status; }

    public void withdraw() {
        status = "Withdrawn";
        grade = null;
    }

    public String getEnrollmentDate() { return enrollmentDate; }
}
