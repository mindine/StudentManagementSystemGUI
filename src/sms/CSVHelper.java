package sms;

import java.io.*;
import java.util.*;

/**
 * Provides CSV read/write operations for students, courses, enrollments, and grades.
 */
public class CSVHelper {

    private static final String STUDENT_FILE = "data/students.csv";
    private static final String COURSE_FILE = "data/courses.csv";
    private static final String ENROLLMENT_FILE = "data/enrollments.csv";
    private static final String GRADE_FILE = "data/grades.csv";

    /**
     * Saves all student records to CSV.
     */
    public static void saveStudents(Map<String, Student> students) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))) {
            for (Student s : students.values()) {
                writer.println(s.getStudentId() + "," + s.getStudentName() + "," + s.getStudentEmail());
            }
        }
    }

    /**
     * Saves all course records to CSV.
     */
    public static void saveCourses(Map<String, Course> courses) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILE))) {
            for (Course c : courses.values()) {
                writer.println(c.getCourseCode() + "," + c.getCourseName() + "," + c.getMaxCapacity() + "," + c.getCurrentEnrollment());
            }
        }
    }

    /**
     * Saves all student-course enrollment mappings to CSV.
     */
    public static void saveEnrollments(Map<String, Student> students) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ENROLLMENT_FILE))) {
            for (Student s : students.values()) {
                for (Course c : s.getEnrolledCourses()) {
                    writer.println(s.getStudentId() + "," + c.getCourseCode());
                }
            }
        }
    }

    /**
     * Saves all student grades for courses to CSV.
     */
    public static void saveGrades(Map<String, Student> students) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(GRADE_FILE))) {
            for (Student s : students.values()) {
                for (Map.Entry<Course, Double> entry : s.getStudentCourseGrades().entrySet()) {
                    writer.println(s.getStudentId() + "," + entry.getKey().getCourseCode() + "," + entry.getValue());
                }
            }
        }
    }

    /**
     * Loads all CSV data into the given student and course maps.
     */
    public static void loadAllData(Map<String, Student> students, Map<String, Course> courses) throws Exception {
        // Load Courses
        try (Scanner scanner = new Scanner(new File(COURSE_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                Course course = new Course(parts[0], parts[1], Integer.parseInt(parts[2]));
                for (int i = 0; i < Integer.parseInt(parts[3]); i++) {
                    course.incrementEnrollment();  // Maintain enrollment count
                }
                courses.put(course.getCourseCode(), course);
            }
        }

        // Load Students
        try (Scanner scanner = new Scanner(new File(STUDENT_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                Student student = new Student(parts[1], parts[2]); // name, email
                student.setStudentId(parts[0]); // restore ID
                students.put(student.getStudentId(), student);
            }
        }

        // Load Enrollments
        try (Scanner scanner = new Scanner(new File(ENROLLMENT_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                Student s = students.get(parts[0]);
                Course c = courses.get(parts[1]);
                if (s != null && c != null) {
                    s.getEnrolledCourses().add(c);
                }
            }
        }

        // Load Grades
        try (Scanner scanner = new Scanner(new File(GRADE_FILE))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                Student s = students.get(parts[0]);
                Course c = courses.get(parts[1]);
                if (s != null && c != null) {
                    s.getStudentCourseGrades().put(c, Double.parseDouble(parts[2]));
                }
            }
        }
    }
}
