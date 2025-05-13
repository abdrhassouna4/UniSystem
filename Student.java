import java.util.*;

class Student extends User {
    private String studentId;
    private String admissionDate;
    private String academicStatus;
    private List<Course> enrolledCourses;
    private Map<Course, String> grades;

    public Student(String userId, String username, String password, String name, String email, String contactInfo, String studentId, String admissionDate, String academicStatus) {
        super(userId, username, password, name, email, contactInfo);
        this.studentId = studentId;
        this.admissionDate = admissionDate;
        this.academicStatus = academicStatus;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getStudentId() { return studentId; }

    public void receiveGrade(Course course, String grade) {
        grades.put(course, grade);
    }

    public String getGrade(String courseId) {
        for (Map.Entry<Course, String> entry : grades.entrySet()) {
            if (entry.getKey().getCourseId().equals(courseId)) {
                return entry.getValue();
            }
        }
        return "N/A";
    }

    public void dropCourse(Course course) {
        if (enrolledCourses.remove(course)) {
            course.dropStudent(this);
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Failed to drop course.");
        }
    }

    public void viewGrades() {
        for (Map.Entry<Course, String> entry : grades.entrySet()) {
            System.out.println("Course: " +  ", Grade: " + entry.getValue());
        }
    }
    public void printAvailableCourses(University university) {
        university.printAllCourses();  // Call the university's method to print all courses
    }

    public void calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;
        for (Map.Entry<Course, String> entry : grades.entrySet()) {
            totalPoints += entry.getKey().getCreditHours() * getGradePoints(entry.getValue());
            totalCredits += entry.getKey().getCreditHours();
        }
        if (totalCredits == 0) {
            System.out.println("No graded courses to calculate GPA.");
            return;
        }
        double gpa = totalPoints / totalCredits;
        System.out.println("GPA: " + gpa);
    }

    private double getGradePoints(String grade) {
        return switch (grade) {
            case "A" -> 4.0;
            case "B" -> 3.0;
            case "C" -> 2.0;
            case "D" -> 1.0;
            case "F" -> 0.0;
            default -> 0.0;
        };
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void logout() {
        System.out.println("Logged out successfully.");
    }

    @Override
    public void updateProfile(String name, String email, String contactInfo) {
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
        System.out.println("Profile updated successfully.");
    }
}
