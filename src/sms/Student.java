package sms;

import java.util.*;

public class Student {
    private static int idCounter = 1000;

    private final String studentID;
    private String studentName;
    private String studentEmail;
    private List<Course> enrolledCourses;
    private Map<Course, Double> studentCourseGrades;

    public Student(String name, String email) {
        this.studentID = "UoP-" + idCounter++;
        this.studentName = name;
        this.studentEmail = email;
        this.enrolledCourses = new ArrayList<>();
        this.studentCourseGrades = new HashMap<>();
    }

    // Getters and Setters
    public String getStudentId() { return studentID; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String name) { this.studentName = name; }
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String email) { this.studentEmail = email; }

    public List<Course> getEnrolledCourses() { return enrolledCourses; }
    public Map<Course, Double> getStudentCourseGrades() { return studentCourseGrades; }
}
