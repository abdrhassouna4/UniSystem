import java.util.*;

class Department {
    private String departmentId;
    private String Departmentname;
    private List<Faculty> faculty;
    private List<Course> offeredCourses;

    public Department(String departmentId, String name) {
        this.departmentId = departmentId;
        this.Departmentname = name;
        this.faculty = new ArrayList<>();
        this.offeredCourses = new ArrayList<>();
    }
    public String getDepartmentName() {
        return Departmentname;
    }

    public void addFaculty(Faculty facultyMember) {
        faculty.add(facultyMember);
    }

    public void removeFaculty(Faculty facultyMember) {
        faculty.remove(facultyMember);
    }

    public void addCourse(Course course) {
        offeredCourses.add(course);
    }

    public List<Faculty> getFacultyList() {
        return faculty;
    }
}
