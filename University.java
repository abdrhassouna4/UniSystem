import java.util.*;

class University {
    private List<Department> departments;
    private List<User> users;
    private Map<String, Course> coursesMap;  // Using Map to store courses by course ID
    private String academicCalendar;

    public University() {
        this.departments = new ArrayList<>();
        this.users = new ArrayList<>();
        this.coursesMap = new HashMap<>();  // Initializing coursesMap
    }

    public void registerStudent(Student student) {
        users.add(student);
        System.out.println("Student registered successfully.");
    }

    public void hireFaculty(Faculty faculty) {
        users.add(faculty);
        System.out.println("Faculty hired successfully.");
    }

    public void createDepartment(Department department) {
        departments.add(department);
        System.out.println("Department created successfully.");
    }

    // Offer a course by adding it to the coursesMap
    public void offerCourse(Course course) {
        coursesMap.put(course.getCourseId(), course);
        System.out.println("Course offered successfully.");
    }

    // Print all courses available
    public void printAllCourses() {
        if (coursesMap.isEmpty()) {
            System.out.println("No courses are available.");
        } else {
            System.out.println("Available Courses:");
            for (Course course : coursesMap.values()) {
                System.out.println("- " + course.getCourseName());
            }
        }
    }

    // Get all courses from the coursesMap
    public List<Course> getCourses() {
        return new ArrayList<>(coursesMap.values());  // Converting the Map values to a List
    }

    // Get a specific course by its ID from the coursesMap
    public Course getCourseById(String courseId) {
        return coursesMap.get(courseId);
    }
}
