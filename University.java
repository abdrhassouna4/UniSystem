import java.util.*;

public class University {
    private String name;
    private List<Student> students;
    private List<Faculty> facultyMembers;
    private List<Department> departments;
    private List<Course> courses;

    // Constructor for the University class
    public University(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.facultyMembers = new ArrayList<>();
        this.departments = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    // ✅ Register a new student in the university
    public void registerStudent(Student student) {
        students.add(student);
        System.out.println("Student " + student.getName() + " has been registered.");
    }

    // ✅ Hire a new faculty member
    public void hireFaculty(Faculty faculty) {
        facultyMembers.add(faculty);
        System.out.println("Faculty member " + faculty.getName() + " has been hired.");
    }

    // ✅ Create a new department
    public void createDepartment(Department department) {
        departments.add(department);
        System.out.println("Department " + department.getDepartmentName() + " has been created.");
    }

    // ✅ Offer a new course at the university
    public void offerCourse(Course course) {
        courses.add(course);
        System.out.println("Course " + course.getTitle() + " has been offered.");
    }

    // Getter methods for university attributes
    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Faculty> getFacultyMembers() {
        return facultyMembers;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
