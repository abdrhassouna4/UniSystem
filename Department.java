public class Department {
    private String departmentId;
    private String name;
    private Instructor[] faculty;
    private Course[] offeredCourses;
    private int facultyCount;
    private int courseCount;

    public Department(String departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
        this.faculty = new Instructor[100]; // Initial capacity for faculty
        this.offeredCourses = new Course[100]; // Initial capacity for courses
        this.facultyCount = 0;
        this.courseCount = 0;
    }

    public void addFaculty(Instructor instructor) {
        if (facultyCount < faculty.length) {
            faculty[facultyCount++] = instructor;
            System.out.println("Faculty member added to department.");
        } else {
            System.out.println("Cannot add more faculty members.");
        }
    }

    public void removeFaculty(String instructorId) {
        boolean found = false;
        for (int i = 0; i < facultyCount; i++) {
            if (faculty[i].getUserId().equals(instructorId)) {
                for (int j = i; j < facultyCount - 1; j++) {
                    faculty[j] = faculty[j + 1];
                }
                faculty[facultyCount - 1] = null;
                facultyCount--;
                found = true;
                System.out.println("Faculty member removed.");
                break;
            }
        }
        if (!found) {
            System.out.println("Faculty member not found.");
        }
    }

    public void addCourse(Course course) {
        if (courseCount < offeredCourses.length) {
            offeredCourses[courseCount++] = course;
            System.out.println("Course added to department.");
        } else {
            System.out.println("Cannot add more courses.");
        }
    }

    public void getFacultyList() {
        System.out.println("Faculty in " + name + " Department:");
        for (int i = 0; i < facultyCount; i++) {
            System.out.println("- " + faculty[i].getName() + " (" + faculty[i].getUserId() + ")");
        }
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }
}
