
package sms;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import sms.Course;
import sms.CourseManagement;
import sms.Student;

public class SMS extends JFrame {

    public SMS() {
        setTitle("UoPeople Student Management System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        instantiateMenuBar();
        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        getContentPane().removeAll();

        JLabel title = new JLabel("<html><center><h2>Welcome to the Demo UoPeople Student Management System (SMS)</h2><p>Please use the Menu bar functions to navigate the application.</p></center></html>", SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(100, 30, 30, 30));

        JLabel logo = new JLabel(new ImageIcon("data/uopeople_logo.png"));
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(title, BorderLayout.SOUTH);
        getContentPane().add(logo, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void instantiateMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Student Management
        JMenu studentMenu = new JMenu("Student Management");
        JMenuItem addStudent = new JMenuItem("Add Student");
        JMenuItem updateStudent = new JMenuItem("Update Student");
        JMenuItem viewStudents = new JMenuItem("View Student Details");

        addStudent.addActionListener(e -> showAddStudentPanel());
        updateStudent.addActionListener(e -> showUpdateStudentPanel());
        viewStudents.addActionListener(e -> showViewStudentDetailsPanel());

        studentMenu.add(addStudent);
        studentMenu.addSeparator();
        studentMenu.add(updateStudent);
        studentMenu.addSeparator();
        studentMenu.add(viewStudents);

        // Course Enrollment
        JMenu courseMenu = new JMenu("Course Enrollment");
        JMenuItem addCourse = new JMenuItem("Add Course");
        JMenuItem enrollStudent = new JMenuItem("Enroll Student");

        addCourse.addActionListener(e -> showAddCoursePanel());
        enrollStudent.addActionListener(e -> showEnrollStudentPanel());

        courseMenu.add(addCourse);
        courseMenu.addSeparator();
        courseMenu.add(enrollStudent);

        // Grade Management
        JMenu gradeMenu = new JMenu("Grade Management");
        JMenuItem assignGrade = new JMenuItem("Assign Grade");
        assignGrade.addActionListener(e -> showAssignGradePanel());
        gradeMenu.add(assignGrade);

        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        menuBar.add(gradeMenu);
        setJMenuBar(menuBar);

        // Add action listener for the "Add Course" menu item
        addCourse.addActionListener(e -> showAddCoursePanel());
    }

    private void showAddStudentPanel() {
        getContentPane().removeAll();

        JLabel titleLabel = new JLabel("<html><h2>Add Student</h2><p>Enter student information:</p></html>");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Email are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            CourseManagement.addStudent(name, email);
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            nameField.setText("");
            emailField.setText("");
        });

        JPanel form = new JPanel(new GridLayout(3, 2));
        form.add(new JLabel("Name: "));
        form.add(nameField);
        form.add(new JLabel("Email: "));
        form.add(emailField);
        form.add(addButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titleLabel, BorderLayout.NORTH);
        getContentPane().add(form, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void showUpdateStudentPanel() {
        getContentPane().removeAll();
        JPanel studentListPanel = new JPanel();
        studentListPanel.setLayout(new BoxLayout(studentListPanel, BoxLayout.Y_AXIS));
        studentListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Map<String, Student> students = CourseManagement.getStudents();

        if (students.isEmpty()) {
            studentListPanel.add(new JLabel("No students found."));
        } else {
            for (Map.Entry<String, Student> entry : students.entrySet()) {
                Student s = entry.getValue();
                JLabel label = new JLabel("[ " + s.getStudentId() + " ] " + s.getStudentName() + " | " + s.getStudentEmail());
                JButton updateBtn = new JButton("Update");
                updateBtn.addActionListener(e -> showStudentUpdateForm(s));

                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                row.add(label);
                row.add(updateBtn);
                studentListPanel.add(row);
            }
        }

        JScrollPane scrollPane = new JScrollPane(studentListPanel);
        getContentPane().add(scrollPane);
        revalidate();
        repaint();
    }

    private void showStudentUpdateForm(Student student) {
        getContentPane().removeAll();

        JTextField nameField = new JTextField(student.getStudentName(), 20);
        JTextField emailField = new JTextField(student.getStudentEmail(), 20);
        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            student.setStudentName(nameField.getText());
            student.setStudentEmail(emailField.getText());
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
            showUpdateStudentPanel();
        });

        JPanel form = new JPanel(new GridLayout(3, 2));
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Email:"));
        form.add(emailField);
        form.add(saveButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JLabel("<html><h2>Update Student</h2></html>"), BorderLayout.NORTH);
        getContentPane().add(form, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // Method to show student details
    private void showViewStudentDetailsPanel() {
        getContentPane().removeAll();

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Map<String, Student> students = CourseManagement.getStudents();

        if (students.isEmpty()) {
            listPanel.add(new JLabel("No students found."));
        } else {
            for (Student student : students.values()) {
                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel label = new JLabel("[ " + student.getStudentId() + " ] " + student.getStudentName());

                JButton viewBtn = new JButton("View Details");
                viewBtn.addActionListener(e -> showStudentDetails(student));

                row.add(label);
                row.add(viewBtn);
                listPanel.add(row);
            }
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        getContentPane().add(scrollPane);

        revalidate();
        repaint();
    }

    // Method to show student details in a new panel
    private void showStudentDetails(Student student) {
        getContentPane().removeAll();

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(student.getStudentId()).append("\n");
        sb.append("Name     : ").append(student.getStudentName()).append("\n");
        sb.append("Email    : ").append(student.getStudentEmail()).append("\n\n");

        sb.append("Enrolled Courses:\n");
        if (student.getEnrolledCourses().isEmpty()) {
            sb.append("  - No courses enrolled.\n");
        } else {
            for (Course course : student.getEnrolledCourses()) {
                sb.append("  - ").append(course.getCourseCode()).append(" - ").append(course.getCourseName());

                Double grade = student.getStudentCourseGrades().get(course);
                if (grade != null) {
                    sb.append(" [Grade: ").append(grade).append("]");
                }
                sb.append("\n");
            }
        }

        detailsArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(detailsArea);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showViewStudentDetailsPanel());

        JPanel southPanel = new JPanel();
        southPanel.add(backButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(southPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void showEnrollStudentPanel() {
        getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("<html><h2>Enroll Student in a Course</h2></html>");
        panel.add(title, BorderLayout.NORTH);

        JComboBox<Course> courseDropdown = new JComboBox<>();
        JComboBox<String> studentDropdown = new JComboBox<>();
        for (Student s : CourseManagement.getStudents().values()) {
            studentDropdown.addItem(s.getStudentId() + " - " + s.getStudentName());
        }
        for (Course c : CourseManagement.getCourses()) {
            courseDropdown.addItem(c);
        }

        if (courseDropdown.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No courses available. Please add a course first.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            courseDropdown.setSelectedIndex(0);
        }

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        topPanel.add(new JLabel("Select Student:"));
        topPanel.add(studentDropdown);
        topPanel.add(new JLabel("Select Course:"));
        topPanel.add(courseDropdown);
        topPanel.add(new JLabel()); // spacer

        JButton enrollButton = new JButton("Enroll");
        topPanel.add(enrollButton);

        panel.add(topPanel, BorderLayout.NORTH);

        enrollButton.addActionListener(e -> {
            if (studentDropdown.getSelectedItem() == null || courseDropdown.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select both a student and a course.");
                return;
            }

            String studentId = ((String) studentDropdown.getSelectedItem()).split(" - ")[0];
            Course selectedCourse = (Course) courseDropdown.getSelectedItem();

            String result = CourseManagement.enrollStudent(studentId, selectedCourse.getCourseCode());
            JOptionPane.showMessageDialog(this, result);
        });

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    private void showAssignGradePanel() {
        getContentPane().removeAll();
        getContentPane().add(new JLabel("Assign Grade Panel (To be implemented)", SwingConstants.CENTER));
        revalidate();
        repaint();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("<html><h2>Assign Grade to Student</h2></html>");
        panel.add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Student selection dropdown
        JLabel studentLabel = new JLabel("Select Student:");
        JComboBox<String> studentDropdown = new JComboBox<>();

        studentDropdown.addItem(""); // blank item as default
        for (Student s : CourseManagement.getStudents().values()) {
            studentDropdown.addItem(s.getStudentId() + " - " + s.getStudentName());
        }
        studentDropdown.setSelectedIndex(0); // selects the blank item


        inputPanel.add(studentLabel);
        inputPanel.add(studentDropdown);

        // Course selection dropdown
        JLabel courseLabel = new JLabel("Select Course:");
        JComboBox<String> courseDropdown = new JComboBox<>();

        inputPanel.add(courseLabel);
        inputPanel.add(courseDropdown);

        // Grade input field
        JLabel gradeLabel = new JLabel("Enter Grade:");
        JTextField gradeField = new JTextField();

        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);

        panel.add(inputPanel, BorderLayout.CENTER);

        studentDropdown.addActionListener(e -> {
            courseDropdown.removeAllItems(); // starts blank
            String selectedId = ((String) studentDropdown.getSelectedItem()).split(" - ")[0];
            Student selectedStudent = CourseManagement.getStudents().get(selectedId);

            for (Course c : selectedStudent.getEnrolledCourses()) {
                courseDropdown.addItem(c.getCourseCode());
            }
        });

        // Assign Grade button
        JButton assignBtn = new JButton("Assign Grade");
        assignBtn.addActionListener(e -> {
            String selectedStudentStr = (String) studentDropdown.getSelectedItem();
            String courseCode = (String) courseDropdown.getSelectedItem();
            String grade = gradeField.getText().trim();

            if (selectedStudentStr == null || selectedStudentStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a student.");
                return;
            }

            if (courseCode == null || courseCode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a course.");
                return;
            }

            if (grade.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a grade.");
                return;
            }

            // Extract student ID from dropdown display format "ID - Name"
            String studentId = selectedStudentStr.split(" - ")[0];

            String result = CourseManagement.assignGrade(studentId, courseCode, grade);
            JOptionPane.showMessageDialog(this, result);
        });


        panel.add(assignBtn, BorderLayout.SOUTH);

        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    // Show panel to add a new course
    private void showAddCoursePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField codeField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField capacityField = new JTextField();

        panel.add(new JLabel("Course Code:"));
        panel.add(codeField);
        panel.add(new JLabel("Course Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Capacity:"));
        panel.add(capacityField);

        JButton addButton = new JButton("Add Course");
        panel.add(new JLabel()); // Empty cell
        panel.add(addButton);

        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();

        addButton.addActionListener(e -> {
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            String capacityText = capacityField.getText().trim();

            try {
                int capacity = Integer.parseInt(capacityText);
                String result = CourseManagement.addCourse(code, name, capacity);
                JOptionPane.showMessageDialog(this, result);
                showWelcomeMessage();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid capacity: must be a number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }


}
