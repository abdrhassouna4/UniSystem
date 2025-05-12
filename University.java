public class University {
    private Department[] departments;
    private User[] users;
    private Course[] courses;
    private String academicCalendar;

    private int departmentCount;
    private int userCount;
    private int courseCount;

    public University() {
        this.departments = new Department[50]; // Initial capacity for departments
        this.users = new User[200];            // Initial capacity for users
        this.courses = new Course[200];        // Initial capacity for courses
        this.departmentCount = 0;
        this.userCount = 0;
        this.courseCount = 0;
        this.academicCalendar = "";
    }

    public void registerStudent(Student student) {
        if (userCount < users.length) {
            users[userCount++] = student;
            System.out.println("Student registered.");
        } else {
            System.out.println("Cannot register more users.");
        }
    }

    public void hireFaculty(Instructor instructor) {
        if (userCount < users.length) {
            users[userCount++] = instructor;
            System.out.println("Faculty hired.");
        } else {
            System.out.println("Cannot hire more users.");
        }
    }

    public void createDepartment(Department department) {
        if (departmentCount < departments.length) {
            departments[departmentCount++] = department;
            System.out.println("Department created.");
        } else {
            System.out.println("Cannot add more departments.");
        }
    }
}
