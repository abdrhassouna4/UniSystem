import java.util.List;
import java.util.ArrayList;

public class Faculty extends User {
    private String facultyId;
    private String department;
    private String expertise;  // Expertise in a specific area
    private List<Course> coursesTeaching;  // List of courses the faculty is teaching

    // Constructor for Faculty
    public Faculty(String username, String password, String name, String email, String contactInfo,
                   String facultyId, String department, String expertise) {
        super(username, password, name, email, contactInfo);
        this.facultyId = facultyId;
        this.department = department;
        this.expertise = expertise;
        this.coursesTeaching = new ArrayList<>();
    }


    // Getter and Setter for expertise
    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    // Getter and Setter for coursesTeaching
    public List<Course> getCoursesTeaching() {
        return coursesTeaching;
    }

    public void setCoursesTeaching(List<Course> coursesTeaching) {
        this.coursesTeaching = coursesTeaching;
    }

    // Assign grade to a student for a course
    public void assignGrade(Student student, Course course, String grade) {
        if (coursesTeaching.contains(course)) {
            // Logic to assign grade (you might want to save the grade in an Enrollment object)
            System.out.println("Assigned grade " + grade + " to " + student.getName() + " for course: " + course.getTitle());
        } else {
            System.out.println("Faculty does not teach this course.");
        }
    }

    // Manage the course by adding/removing students, setting syllabus, etc.
    public void manageCourse(Course course) {
        if (coursesTeaching.contains(course)) {
            System.out.println("Managing course: " + course.getTitle());
            // Logic for course management (e.g., update course schedule, materials)
        } else {
            System.out.println("Faculty is not assigned to this course.");
        }
    }

    // Set office hours for the faculty
    public void setOfficeHours(String hours) {
        System.out.println("Office hours for " + name + ": " + hours);
    }

    // View student roster for a course
    public void viewStudentRoster(Course course) {
        if (coursesTeaching.contains(course)) {
            System.out.println("Student roster for " + course.getTitle() + ":");
            for (Student student : course.getEnrolledStudents()) {
                System.out.println("- " + student.getName());
            }
        } else {
            System.out.println("Faculty is not teaching this course.");
        }
    }

    @Override
    public void showMenu() {
        System.out.println("Welcome, Faculty: " + name);
        System.out.println("1. Assign Grade to Student");
        System.out.println("2. Manage Course");
        System.out.println("3. Set Office Hours");
        System.out.println("4. View Student Roster");
        System.out.println("5. Update Profile");
        System.out.println("6. Logout");
    }

    // Getter methods for faculty attributes
    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
