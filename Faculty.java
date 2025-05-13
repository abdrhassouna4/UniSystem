import java.util.*;

public class Faculty extends User {
    private String facultyId;
    private String department;
    private List<Course> teachingCourses;


    public Faculty(String userId, String username, String password, String name, String email, String contactInfo, String facultyId, String department) {
        super(userId, username, password, name, email, contactInfo);
        this.facultyId = facultyId;
        this.department = department;
        this.teachingCourses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        course.setInstructor(this);
        teachingCourses.add(course);
    }

    public void assignGrade(Course course, Student student, String grade) {
        if (teachingCourses.contains(course)) {
            course.assignGrade(student, grade);
            System.out.println("Grade assigned.");
        } else {
            System.out.println("You are not assigned to this course.");
        }
    }

    public void viewCourseRoster(Course course) {
        if (teachingCourses.contains(course)) {
            course.printRoster();
        } else {
            System.out.println("You are not the instructor for this course.");
        }
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public void logout() {
        System.out.println("Faculty logged out.");
    }

    @Override
    public void updateProfile(String name, String email, String contactInfo) {
        this.name = name;
        this.email = email;
        this.contactInfo = contactInfo;
        System.out.println("Profile updated.");
    }

    public List<Course> getTeachingCourses() {
        return teachingCourses;
    }
}
