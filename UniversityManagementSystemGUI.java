import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UniversityManagementSystemGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel dashboardPanel;
    private JPanel studentRegistrationPanel;
    private JPanel courseManagementPanel;
    private JPanel academicInfoPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UniversityManagementSystemGUI window = new UniversityManagementSystemGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UniversityManagementSystemGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("University Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Create and add panels
        dashboardPanel = createDashboardPanel();
        studentRegistrationPanel = createStudentRegistrationPanel();
        courseManagementPanel = createCourseManagementPanel();
        academicInfoPanel = createAcademicInfoPanel();

        mainPanel.add(dashboardPanel, "Dashboard");
        mainPanel.add(studentRegistrationPanel, "Student Registration");
        mainPanel.add(courseManagementPanel, "Course Management");
        mainPanel.add(academicInfoPanel, "Academic Info");

        cardLayout.show(mainPanel, "Dashboard");

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JButton studentRegistrationButton = new JButton("Student Registration");
        studentRegistrationButton.addActionListener(e -> cardLayout.show(mainPanel, "Student Registration"));
        panel.add(studentRegistrationButton);

        JButton courseManagementButton = new JButton("Course Management");
        courseManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "Course Management"));
        panel.add(courseManagementButton);

        JButton academicInfoButton = new JButton("Academic Info");
        academicInfoButton.addActionListener(e -> cardLayout.show(mainPanel, "Academic Info"));
        panel.add(academicInfoButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        return panel;
    }

    private JPanel createStudentRegistrationPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField studentIdField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Contact Info:"));
        panel.add(contactField);
        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Student Registered!");
            cardLayout.show(mainPanel, "Dashboard");
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));

        panel.add(registerButton);
        panel.add(backButton);

        return panel;
    }

    private JPanel createCourseManagementPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JButton addCourseButton = new JButton("Add New Course");
        addCourseButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Add Course Feature Coming Soon"));
        panel.add(addCourseButton);

        JButton viewCoursesButton = new JButton("View All Courses");
        viewCoursesButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "View Courses Feature Coming Soon"));
        panel.add(viewCoursesButton);

        JButton assignCourseButton = new JButton("Assign Course to Student");
        assignCourseButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Assign Course Feature Coming Soon"));
        panel.add(assignCourseButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        panel.add(backButton);

        return panel;
    }

    private JPanel createAcademicInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea academicInfoArea = new JTextArea(10, 40);
        academicInfoArea.setText("Your Academic Info:\n\n- Courses Enrolled: \n- Grades: \n- GPA: ");
        academicInfoArea.setEditable(false);
        panel.add(new JScrollPane(academicInfoArea), BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Dashboard"));
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }
}
