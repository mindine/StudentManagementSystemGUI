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
     * Generates a new student ID based on a static counter.
     * Format: "S1001", "S1002", ...
     */
    private String generateStudentId() {
        return "S" + (++idCounter);
    }

    /**
     * Syncs the static ID counter with the maximum ID found in persisted students.
     * Helps prevent duplicate IDs after CSV reload.
     */
    public static void syncIdCounter(Collection<Student> students) {
        for (Student s : students) {
            try {
                int idNum = Integer.parseInt(s.getStudentId().substring(1));
                if (idNum > idCounter) {
                    idCounter = idNum;
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }

    // --- Getters and Setters ---

    public String getStudentId() {
        return studentId;
    }

    /**
     * Allows restoring original student ID during data loading from CSV.
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

    // --- Optional: Helpful Overrides for Set/Map usage ---

    /**
     * Defines equality based on student ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return studentId.equals(other.studentId);
    }

    /**
     * Hashcode based on student ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    /**
     * Custom string representation for debugging.
     */
    @Override
    public String toString() {
        return studentId + " - " + studentName;
    }
}
