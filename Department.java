import java.util.*;

public class Department {
    private String departmentName;
    private List<Faculty> facultyList;
    private List<Course> courseList;

    // Constructor to initialize the department
    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.facultyList = new ArrayList<>();
        this.courseList = new ArrayList<>();
    }

    // ✅ Method to add a faculty member to the department
    public void addFaculty(Faculty faculty) {
        facultyList.add(faculty);
        System.out.println("Faculty " + faculty.getName() + " added to the department: " + departmentName);
    }

    // ✅ Method to remove a faculty member from the department
    public void removeFaculty(Faculty faculty) {
        if (facultyList.contains(faculty)) {
            facultyList.remove(faculty);
            System.out.println("Faculty " + faculty.getName() + " removed from the department: " + departmentName);
        } else {
            System.out.println("Faculty " + faculty.getName() + " not found in the department: " + departmentName);
        }
    }

    // ✅ Method to add a course to the department
    public void addCourse(Course course) {
        courseList.add(course);
        System.out.println("Course " + course.getTitle() + " added to the department: " + departmentName);
    }

    // ✅ Method to get the list of faculty members in the department
    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    // ✅ Method to get the list of courses offered by the department
    public List<Course> getCourseList() {
        return courseList;
    }

    // ✅ Getter method for department name
    public String getDepartmentName() {
        return departmentName;
    }
}
