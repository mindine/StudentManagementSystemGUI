package sms;

import java.util.*;

/**
 * Represents a student in the Student Management System.
 */
public class Student {
    private static int idCounter = 1000; // Static counter to generate unique IDs

    private String studentId;
    private String studentName;
    private String studentEmail;
    private List<Course> enrolledCourses;
    private Map<Course, Double> studentCourseGrades;

    /**
     * Constructs a Student with a given name and email.
     * Automatically generates a unique student ID.
     */
    public Student(String name, String email) {
        this.studentName = name;
        this.studentEmail = email;
        this.studentId = generateStudentId();
        this.enrolledCourses = new ArrayList<>();
        this.studentCourseGrades = new HashMap<>();
    }

    /**
     * Generates a new student ID.
     */
    private String generateStudentId() {
        return "S" + (++idCounter);
    }

    // --- Getters and Setters ---

    public String getStudentId() {
        return studentId;
    }

    /**
     * Allows setting student ID manually (e.g., during data loading from CSV).
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public Map<Course, Double> getStudentCourseGrades() {
        return studentCourseGrades;
    }
}
