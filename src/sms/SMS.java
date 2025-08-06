package sms;

import javax.swing.*;
import java.awt.*;

public class SMS extends JFrame {

    public SMS() {
        setTitle("UoPeople Student Management System");
        setSize(900, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize Menu Bar
        instantiateMenuBar();

        // Show welcome message by default
        showWelcomeMessage();
    }

    // 📌 1. Display welcome screen
    private void showWelcomeMessage() {
        String welcome = "<html>"
                + "<div style='text-align:center;'>"
                + "<h1>Welcome to the UoPeople Student Management System</h1>"
                + "<p>Please use the menu bar above to navigate the application.</p>"
                + "</div>"
                + "</html>";

        JLabel welcomeLabel = new JLabel(welcome);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Container contentPane = this.getContentPane();
        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(welcomeLabel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // 📌 2. Setup menu bar with 3 top-level menus
    private void instantiateMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Student Management
        JMenu studentMenu = new JMenu("Student Management");
        JMenuItem addStudent = new JMenuItem("Add Student");
        JMenuItem updateStudent = new JMenuItem("Update Student");
        JMenuItem viewStudents = new JMenuItem("View Student Details");

        studentMenu.add(addStudent);
        studentMenu.addSeparator();
        studentMenu.add(updateStudent);
        studentMenu.addSeparator();
        studentMenu.add(viewStudents);

        // Course Enrollment
        JMenu courseMenu = new JMenu("Course Enrollment");
        JMenuItem addCourse = new JMenuItem("Add Course");
        JMenuItem enrollStudent = new JMenuItem("Enroll Student");

        courseMenu.add(addCourse);
        courseMenu.addSeparator();
        courseMenu.add(enrollStudent);

        // Grade Management
        JMenu gradeMenu = new JMenu("Grade Management");
        JMenuItem assignGrade = new JMenuItem("Assign Grade");
        gradeMenu.add(assignGrade);

        // Action Listeners (empty handlers for now)
        addStudent.addActionListener(e -> showAddStudentPanel());
        updateStudent.addActionListener(e -> showUpdateStudentPanel());
        viewStudents.addActionListener(e -> showViewStudentPanel());

        addCourse.addActionListener(e -> showAddCoursePanel());
        enrollStudent.addActionListener(e -> showEnrollStudentPanel());

        assignGrade.addActionListener(e -> showAssignGradePanel());

        // Add to menu bar
        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        menuBar.add(gradeMenu);

        setJMenuBar(menuBar);
    }

    // Placeholder panel handlers
    private void showAddStudentPanel() {
        switchToMessage("Add Student Panel (To be implemented)");
    }

    private void showUpdateStudentPanel() {
        switchToMessage("Update Student Panel (To be implemented)");
    }

    private void showViewStudentPanel() {
        switchToMessage("View Student Details Panel (To be implemented)");
    }

    private void showAddCoursePanel() {
        switchToMessage("Add Course Panel (To be implemented)");
    }

    private void showEnrollStudentPanel() {
        switchToMessage("Enroll Student in Course Panel (To be implemented)");
    }

    private void showAssignGradePanel() {
        switchToMessage("Assign Grade Panel (To be implemented)");
    }

    // Utility: Show placeholder message
    private void switchToMessage(String message) {
        JLabel label = new JLabel("<html><div style='text-align:center;'>" + message + "</div></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        Container contentPane = this.getContentPane();
        contentPane.removeAll();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(label, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
