import java.util.List; // Correct List import

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class UniversitySystemGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Map<String, String[]> credentials; // username -> [password, role]
    private Map<String, List<String>> studentCourses = new HashMap<>();
    private Map<String, Map<String, String>> grades = new HashMap<>(); // student -> {course -> grade}
    private List<String> courseList = new ArrayList<>();
    private Map<String, String> facultyCourses = new HashMap<>(); // faculty -> course

    public UniversitySystemGUI() {
    super("University Management System");
    credentials = new HashMap<>();
    loadCredentials();

    // Insert sample courses
    courseList.addAll(Arrays.asList("CS101", "MATH201", "ENG105", "BIO110", "PHYS220"));

    // Initialize CardLayout and mainPanel
    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);

    // Adding the panels to the mainPanel
    mainPanel.add(registerPanel(), "Register");
    mainPanel.add(loginPanel(), "Login");

    // Add mainPanel to the frame
    add(mainPanel);

    // Show the Register panel first
    cardLayout.show(mainPanel, "Register");

    // Force layout updates
    revalidate();
    repaint();

    // Set JFrame settings
    setSize(600, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);

    // Add default admin accounts if not already loaded
    credentials.putIfAbsent("Hassouna", new String[]{"2006", "Admin"});
    credentials.putIfAbsent("Aly", new String[]{"2005", "Admin"});

}



    private JPanel registerPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        String[] roles = {"Student", "Faculty", "Admin"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(new JLabel("Role:"));
        panel.add(roleBox);

        JButton registerBtn = new JButton("Register");
        JButton toLoginBtn = new JButton("Go to Login");
        panel.add(registerBtn);
        panel.add(toLoginBtn);

        registerBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            String role = (String) roleBox.getSelectedItem();
            if (!user.isEmpty() && !pass.isEmpty()) {
                credentials.put(user, new String[]{pass, role});
                saveCredential(user, pass, role);
                JOptionPane.showMessageDialog(this, "Registered successfully!");
            }
        });

        toLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        return panel;
    }

    private JPanel loginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);

        JButton loginBtn = new JButton("Login");
        JButton toRegisterBtn = new JButton("Go to Register");
        panel.add(loginBtn);
        panel.add(toRegisterBtn);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (credentials.containsKey(user) && credentials.get(user)[0].equals(pass)) {
                String role = credentials.get(user)[1];
                if (role.equals("Student")) mainPanel.add(studentPanel(user), "Student");
                else if (role.equals("Faculty")) mainPanel.add(facultyPanel(user), "Faculty");
                else if (role.equals("Admin")) mainPanel.add(adminPanel(user), "Admin");
                cardLayout.show(mainPanel, role);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        });

        toRegisterBtn.addActionListener(e -> cardLayout.show(mainPanel, "Register"));
        return panel;
    }

    private JPanel studentPanel(String username) {
        JPanel panel = new JPanel(new GridLayout(7, 1));
        panel.add(new JLabel("Welcome, Student " + username));

        JButton viewCoursesBtn = new JButton("View Available Courses");
        JButton registerCourseBtn = new JButton("Register Course");
        JButton dropCourseBtn = new JButton("Drop Course");
        JButton viewGradesBtn = new JButton("View Grades");
        JButton gpaBtn = new JButton("Calculate GPA");
        JButton logoutBtn = new JButton("Logout");

        panel.add(viewCoursesBtn);
        panel.add(registerCourseBtn);
        panel.add(dropCourseBtn);
        panel.add(viewGradesBtn);
        panel.add(gpaBtn);
        panel.add(logoutBtn);

        viewCoursesBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Courses: " + courseList));

        registerCourseBtn.addActionListener(e -> {
            String course = JOptionPane.showInputDialog("Enter course name to register:");
            if (courseList.contains(course)) {
                studentCourses.computeIfAbsent(username, k -> new ArrayList<>()).add(course);
                JOptionPane.showMessageDialog(this, "Course registered.");
            } else {
                JOptionPane.showMessageDialog(this, "Course not found.");
            }
        });

        dropCourseBtn.addActionListener(e -> {
            String course = JOptionPane.showInputDialog("Enter course name to drop:");
            List<String> list = studentCourses.getOrDefault(username, new ArrayList<>());
            if (list.remove(course)) {
                JOptionPane.showMessageDialog(this, "Course dropped.");
            } else {
                JOptionPane.showMessageDialog(this, "You are not enrolled in this course.");
            }
        });

        viewGradesBtn.addActionListener(e -> {
            Map<String, String> g = grades.getOrDefault(username, new HashMap<>());
            JOptionPane.showMessageDialog(this, "Grades: " + g);
        });

        gpaBtn.addActionListener(e -> {
            Map<String, String> g = grades.getOrDefault(username, new HashMap<>());
            double total = 0;
            int count = 0;
            for (String grade : g.values()) {
                switch (grade.toUpperCase()) {
                    case "A": total += 4.0; break;
                    case "B": total += 3.0; break;
                    case "C": total += 2.0; break;
                    case "D": total += 1.0; break;
                    default: continue;
                }
                count++;
            }
            double gpa = (count == 0) ? 0 : total / count;
            JOptionPane.showMessageDialog(this, "GPA: " + gpa);
        });

        logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        
        return panel;
    }

    private JPanel facultyPanel(String username) {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(new JLabel("Welcome, Faculty " + username));

        JButton assignCourseBtn = new JButton("Assign Course to Faculty");
        JButton assignGradeBtn = new JButton("Assign Grade to Student");
        JButton viewRosterBtn = new JButton("View Course Roster");
        JButton logoutBtn = new JButton("Logout");

        panel.add(assignCourseBtn);
        panel.add(assignGradeBtn);
        panel.add(viewRosterBtn);
        panel.add(logoutBtn);

        assignCourseBtn.addActionListener(e -> {
            String course = JOptionPane.showInputDialog("Enter course to assign to yourself:");
            if (courseList.contains(course)) {
                facultyCourses.put(username, course);
                JOptionPane.showMessageDialog(this, "Course assigned.");
            } else {
                JOptionPane.showMessageDialog(this, "Course not found.");
            }
        });

        assignGradeBtn.addActionListener(e -> {
            String student = JOptionPane.showInputDialog("Enter student username:");
            String grade = JOptionPane.showInputDialog("Enter grade (A-F):");
            String course = facultyCourses.get(username);
            if (course != null && studentCourses.getOrDefault(student, new ArrayList<>()).contains(course)) {
                grades.computeIfAbsent(student, k -> new HashMap<>()).put(course, grade);
                JOptionPane.showMessageDialog(this, "Grade assigned.");
            } else {
                JOptionPane.showMessageDialog(this, "Student not registered in your course.");
            }
        });

        viewRosterBtn.addActionListener(e -> {
            String course = facultyCourses.get(username);
            StringBuilder sb = new StringBuilder("Roster for " + course + ":\n");
            for (Map.Entry<String, List<String>> entry : studentCourses.entrySet()) {
                if (entry.getValue().contains(course)) sb.append(entry.getKey()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        });

        logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        return panel;
    }

    private JPanel adminPanel(String username) {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(new JLabel("Welcome, Admin " + username));

        JButton assignCourseBtn = new JButton("Assign Course to Faculty");
        JButton createCourseBtn = new JButton("Create Course");
        JButton registerStudentBtn = new JButton("Register Student");
        JButton reportBtn = new JButton("Generate Reports");
        JButton logoutBtn = new JButton("Logout");

        panel.add(assignCourseBtn);
        panel.add(createCourseBtn);
        panel.add(registerStudentBtn);
        panel.add(reportBtn);
        panel.add(logoutBtn);

        

        createCourseBtn.addActionListener(e -> {
            String course = JOptionPane.showInputDialog("Enter course name to create:");
            if (!courseList.contains(course)) {
                courseList.add(course);
                JOptionPane.showMessageDialog(this, "Course created.");
            } else {
                JOptionPane.showMessageDialog(this, "Course already exists.");
            }
        });


        assignCourseBtn.addActionListener(e -> {
        String faculty = JOptionPane.showInputDialog("Enter faculty username:");
        if (credentials.containsKey(faculty) && "Faculty".equals(credentials.get(faculty)[1])) {
            String course = JOptionPane.showInputDialog("Enter course name:");
            if (courseList.contains(course)) {
                facultyCourses.put(faculty, course);
                JOptionPane.showMessageDialog(this, "Course assigned to faculty.");
            } else {
                JOptionPane.showMessageDialog(this, "Course does not exist.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid faculty username.");
        }
    });


        reportBtn.addActionListener(e -> generateReports());

        registerStudentBtn.addActionListener(e -> cardLayout.show(mainPanel, "Register"));

        logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        return panel;
    }

    private void loadCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) credentials.put(parts[0], new String[]{parts[1], parts[2]});
            }
        } catch (IOException ignored) {}
    }

    private void saveCredential(String user, String pass, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt", true))) {
            writer.write(user + "," + pass + "," + role);
            writer.newLine();
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniversitySystemGUI::new);
        
    }

        private void generateReports() {
        StringBuilder report = new StringBuilder();

        report.append("===== University System Report =====\n\n");

        // Courses
        report.append("Courses:\n");
        for (String course : courseList) {
            report.append("- ").append(course).append("\n");
        }

        // Faculty assignments
        report.append("\nFaculty Assignments:\n");
        for (Map.Entry<String, String> entry : facultyCourses.entrySet()) {
            report.append("- ").append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }

        // Student enrollments
        report.append("\nStudent Enrollments:\n");
        for (Map.Entry<String, List<String>> entry : studentCourses.entrySet()) {
            report.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // Grades
        report.append("\nGrades:\n");
        for (Map.Entry<String, Map<String, String>> entry : grades.entrySet()) {
            report.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        // Show report
        JTextArea textArea = new JTextArea(report.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        JOptionPane.showMessageDialog(this, scrollPane, "System Report", JOptionPane.INFORMATION_MESSAGE);
    }

    

}
