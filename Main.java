import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        University university = new University();
        Map<String, Student> studentsMap = new HashMap<>();
        Map<String, Course> coursesMap = new HashMap<>();
        Map<String, Faculty> facultyMap = new HashMap<>();
        Map<String, AdminStaff> adminMap = new HashMap<>();

        // Pre-adding courses
        Course c1 = new Course("C101", "Math 101", "Basic Math", 3, 30);
        Course c2 = new Course("C102", "Physics", "Intro to Physics", 4, 25);
        Course c3 = new Course("C103", "Java Programming", "OOP with Java", 4, 30);
        Course c4 = new Course("C104", "Web Development", "Frontend and backend web dev", 3, 30);
        Course c5 = new Course("C105", "Databases", "SQL and relational models", 3, 30);

        // Offer courses only once and print the success message once
        university.offerCourse(c1);
        university.offerCourse(c2);
        university.offerCourse(c3);
        university.offerCourse(c4);
        university.offerCourse(c5);

        // Store courses in the map
        coursesMap.put(c1.getCourseId(), c1);
        coursesMap.put(c2.getCourseId(), c2);
        coursesMap.put(c3.getCourseId(), c3);
        coursesMap.put(c4.getCourseId(), c4);
        coursesMap.put(c5.getCourseId(), c5);

        // Preload two admins
        AdminStaff admin1 = new AdminStaff("A001", "Hassouna", "2006", "Hassouna Admin", "hassouna@university.edu", "1234567890");
        AdminStaff admin2 = new AdminStaff("A002", "Aly", "2005", "Aly Admin", "aly@university.edu", "0987654321");
        adminMap.put("Hassouna", admin1);
        adminMap.put("Aly", admin2);

        while (true) {
            System.out.println("\n=== University System Menu ===");
            System.out.println("1. Register Student");
            System.out.println("2. Faculty Registration");
            System.out.println("3. Student Login");
            System.out.println("4. Faculty Login");
            System.out.println("5. Admin Login");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Register student
                    System.out.print("Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Username: ");
                    String studentUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String studentPassword = scanner.nextLine();
                    System.out.print("Name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Email: ");
                    String studentEmail = scanner.nextLine();
                    System.out.print("Contact: ");
                    String studentContact = scanner.nextLine();
                    System.out.print("Admission Date: ");
                    String admissionDate = scanner.nextLine();
                    System.out.print("Academic Status: ");
                    String academicStatus = scanner.nextLine();

                    Student student = new Student(studentId, studentUsername, studentPassword, studentName, studentEmail, studentContact, studentId, admissionDate, academicStatus);
                    university.registerStudent(student);
                    studentsMap.put(studentUsername, student);
                    break;

                case 2:
                    // Faculty registration
                    System.out.print("Faculty ID: ");
                    String facultyId = scanner.nextLine();
                    System.out.print("Username: ");
                    String facultyUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String facultyPassword = scanner.nextLine();
                    System.out.print("Name: ");
                    String facultyName = scanner.nextLine();
                    System.out.print("Email: ");
                    String facultyEmail = scanner.nextLine();
                    System.out.print("Contact: ");
                    String facultyContact = scanner.nextLine();
                    System.out.print("Department: ");
                    String facultyDept = scanner.nextLine();

                    Faculty faculty = new Faculty(facultyId, facultyUsername, facultyPassword, facultyName, facultyEmail, facultyContact, facultyId, facultyDept);
                    university.hireFaculty(faculty);
                    facultyMap.put(facultyUsername, faculty);
                    System.out.println("Faculty registered successfully.");
                    break;

                case 3:
                    // Student login
                    System.out.print("Username: ");
                    String studentLoginUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String studentLoginPassword = scanner.nextLine();

                    Student stu = studentsMap.get(studentLoginUsername);
                    if (stu != null && stu.login(studentLoginUsername, studentLoginPassword)) {
                        boolean inStudentMenu = true;
                        while (inStudentMenu) {
                            System.out.println("\n-- Student Menu --");
                            System.out.println("1. Register for Course");
                            System.out.println("2. Drop Course");
                            System.out.println("3. View Grades");
                            System.out.println("4. Calculate GPA");
                            System.out.println("5. Logout");
                            System.out.print("Choice: ");
                            int studentChoice = Integer.parseInt(scanner.nextLine());

                            switch (studentChoice) {
                                case 1: // Register for Course
                                    // Display all available courses
                                    System.out.println("\n-- Available Courses --");
                                    for (Course course : coursesMap.values()) {
                                        System.out.println(course.getCourseId() + ": " + course.getCourseName() + " (" + course.getDescription() + ")");
                                    }

                                    System.out.print("Enter Course ID to register: ");
                                    String courseIdToEnroll = scanner.nextLine();
                                    Course courseToEnroll = coursesMap.get(courseIdToEnroll);
                                    if (courseToEnroll != null && courseToEnroll.enrollStudent(stu)) {
                                        System.out.println("Registered successfully for " + courseToEnroll.getCourseName());
                                    } else {
                                        System.out.println("Registration failed.");
                                    }
                                    break;

                                case 2:
                                    System.out.print("Enter Course ID: ");
                                    String courseIdToDrop = scanner.nextLine();
                                    Course courseToDrop = coursesMap.get(courseIdToDrop);
                                    if (courseToDrop != null) {
                                        courseToDrop.dropStudent(stu);
                                        System.out.println("Dropped course.");
                                    }
                                    break;
                                case 3:
                                    for (Course course : coursesMap.values()) {
                                        String grade = course.getGrade(stu);
                                        if (!grade.equals("N/A")) {
                                            System.out.println(course.getCourseName() + ": " + grade);
                                        }
                                    }
                                    break;
                                case 4:
                                    stu.calculateGPA();
                                    break;
                                case 5:
                                    stu.logout();
                                    inStudentMenu = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case 4:
                    // Faculty login
                    System.out.print("Username: ");
                    String facultyLoginUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String facultyLoginPassword = scanner.nextLine();

                    Faculty fac = facultyMap.get(facultyLoginUsername);
                    if (fac != null && fac.login(facultyLoginUsername, facultyLoginPassword)) {
                        boolean inFacultyMenu = true;
                        while (inFacultyMenu) {
                            System.out.println("\n-- Faculty Menu --");
                            System.out.println("1. Assign Course");
                            System.out.println("2. Assign Grade");
                            System.out.println("3. View Course Roster");
                            System.out.println("4. Logout");
                            System.out.print("Choice: ");
                            int facultyChoice = Integer.parseInt(scanner.nextLine());

                            switch (facultyChoice) {
                                case 1: // Assign Course
                                    // Display all available courses
                                    System.out.println("\n-- Available Courses --");
                                    for (Course course : coursesMap.values()) {
                                        System.out.println(course.getCourseId() + ": " + course.getCourseName() + " (" + course.getDescription() + ")");
                                    }

                                    System.out.print("Enter Course ID to assign: ");
                                    String courseIdToAssign = scanner.nextLine();
                                    Course courseToAssign = coursesMap.get(courseIdToAssign);
                                    if (courseToAssign != null) {
                                        fac.addCourse(courseToAssign);
                                        System.out.println("Assigned to " + courseToAssign.getCourseName());
                                    } else {
                                        System.out.println("Invalid course ID.");
                                    }
                                    break;

                                case 2:
                                    System.out.print("Enter Course ID: ");
                                    String courseIdForGrading = scanner.nextLine();
                                    Course courseForGrading = coursesMap.get(courseIdForGrading);
                                    if (courseForGrading != null) {
                                        courseForGrading.printRoster();
                                        System.out.print("Enter Student ID: ");
                                        String studentIdForGrade = scanner.nextLine();
                                        Student foundStudent = null;
                                        for (Student s : courseForGrading.getEnrolledStudents()) {
                                            if (s.getStudentId().equals(studentIdForGrade)) {
                                                foundStudent = s;
                                                break;
                                            }
                                        }
                                        if (foundStudent != null) {
                                            System.out.print("Enter Grade: ");
                                            String grade = scanner.nextLine();
                                            fac.assignGrade(courseForGrading, foundStudent, grade);
                                        } else {
                                            System.out.println("Student not found.");
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter Course ID: ");
                                    String courseIdToView = scanner.nextLine();
                                    Course courseToView = coursesMap.get(courseIdToView);
                                    if (courseToView != null) {
                                        fac.viewCourseRoster(courseToView);
                                    }
                                    break;
                                case 4:
                                    fac.logout();
                                    inFacultyMenu = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case 5:
                    // Admin login
                    System.out.print("Username: ");
                    String adminLoginUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String adminLoginPassword = scanner.nextLine();

                    AdminStaff adminLoggedIn = adminMap.get(adminLoginUsername);
                    if (adminLoggedIn != null && adminLoggedIn.login(adminLoginUsername, adminLoginPassword)) {
                        boolean inAdminMenu = true;
                        while (inAdminMenu) {
                            System.out.println("\n-- Admin Menu --");
                            System.out.println("1. Register Student");
                            System.out.println("2. Create Course");
                            System.out.println("3. Assign Faculty to Course");
                            System.out.println("4. Logout");
                            System.out.print("Choice: ");
                            int adminChoice = Integer.parseInt(scanner.nextLine());

                            switch (adminChoice) {
                                case 1:
                                    // Admin registers student
                                    System.out.print("Student ID: ");
                                    String adminStudentId = scanner.nextLine();
                                    System.out.print("Username: ");
                                    String adminStudentUsername = scanner.nextLine();
                                    System.out.print("Password: ");
                                    String adminStudentPassword = scanner.nextLine();
                                    System.out.print("Name: ");
                                    String adminStudentName = scanner.nextLine();
                                    System.out.print("Email: ");
                                    String adminStudentEmail = scanner.nextLine();
                                    System.out.print("Contact: ");
                                    String adminStudentContact = scanner.nextLine();
                                    System.out.print("Admission Date: ");
                                    String adminStudentDate = scanner.nextLine();
                                    System.out.print("Academic Status: ");
                                    String adminStudentStatus = scanner.nextLine();

                                    Student newStudent = new Student(adminStudentId, adminStudentUsername, adminStudentPassword, adminStudentName, adminStudentEmail, adminStudentContact, adminStudentId, adminStudentDate, adminStudentStatus);
                                    university.registerStudent(newStudent);
                                    studentsMap.put(adminStudentUsername, newStudent);
                                    System.out.println("Student registered successfully.");
                                    break;
                                case 2:
                                    // Admin creates a course
                                    System.out.print("Course ID: ");
                                    String adminCourseId = scanner.nextLine();
                                    System.out.print("Course Name: ");
                                    String adminCourseName = scanner.nextLine();
                                    System.out.print("Description: ");
                                    String adminCourseDesc = scanner.nextLine();
                                    System.out.print("Credit Hours: ");
                                    int adminCourseCredits = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Capacity: ");
                                    int adminCourseCap = Integer.parseInt(scanner.nextLine());

                                    Course newAdminCourse = new Course(adminCourseId, adminCourseName, adminCourseDesc, adminCourseCredits, adminCourseCap);
                                    university.offerCourse(newAdminCourse);
                                    coursesMap.put(adminCourseId, newAdminCourse);
                                    System.out.println("Course created successfully.");
                                    break;

                                case 3:
                                    // Assign faculty to course
                                    System.out.print("Enter Faculty Username: ");
                                    String facultyUsernameToAssign = scanner.nextLine();
                                    Faculty facultyToAssign = facultyMap.get(facultyUsernameToAssign);
                                    if (facultyToAssign != null) {
                                        System.out.print("Enter Course ID: ");
                                        String courseIdToAssignFaculty = scanner.nextLine();
                                        Course courseToAssignFaculty = coursesMap.get(courseIdToAssignFaculty);
                                        if (courseToAssignFaculty != null) {
                                            adminLoggedIn.assignFacultyToCourse(facultyToAssign, courseToAssignFaculty);
                                        } else {
                                            System.out.println("Course not found.");
                                        }
                                    } else {
                                        System.out.println("Faculty not found.");
                                    }
                                    break;

                                case 4:
                                    inAdminMenu = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
            }
        }
    }
}
