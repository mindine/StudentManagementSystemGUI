package sms;

import java.io.IOException;
import java.util.*;

/**
 * Manages all operations related to students, courses, enrollments, and grades.
 */
public class CourseManagement {

    // Maps for quick student and course lookup
    private static Map<String, Student> students = new HashMap<>();
    private static Map<String, Course> courses = new HashMap<>();

    // Load data statically when the application starts
    static {
        try {
            CSVHelper.loadAllData(students, courses);
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // Getter for students
    public static Map<String, Student> getStudents() {
        return students;
    }

    // Getter for courses
    public static Collection<Course> getCourses() {
        return courses.values();
    }

    /**
     * Adds a new student and saves to file.
     */
    public static String addStudent(String name, String email) {
        Student s = new Student(name, email);
        students.put(s.getStudentId(), s);
        try {
            CSVHelper.saveStudents(students);
        } catch (IOException e) {
            System.out.println("Failed to save students: " + e.getMessage());
        }
        return s.getStudentId();
    }

    /**
     * Adds a new course and saves to file.
     */
    public static String addCourse(String code, String name, int capacity) throws Exception {
        Course c = new Course(code, name, capacity);
        courses.put(code, c);
        try {
            CSVHelper.saveCourses(courses);
        } catch (IOException e) {
            System.out.println("Failed to save courses: " + e.getMessage());
        }
        return c.getCourseCode();
    }

    /**
     * Updates student name and email.
     */
    public static String updateStudent(String studentID, String newName, String newEmail) {
        Student student = students.get(studentID);
        if (student != null) {
            student.setStudentName(newName);
            student.setStudentEmail(newEmail);
            try {
                CSVHelper.saveStudents(students);
            } catch (IOException e) {
                System.out.println("Failed to save students: " + e.getMessage());
            }
            return "Student ID " + studentID + " updated successfully.";
        }
        return "Student ID not found.";
    }

    /**
     * Assigns a grade to a student for a course and saves to file.
     * Ensures grade is between 0 and 100.
     * Returns a message to be shown to the user.
     */
    public static String assignGrade(String studentId, String courseCode, String grade) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student == null || course == null) {
            return "Invalid student or course selection.";
        }

        try {
            double numericGrade = Double.parseDouble(grade);

            if (numericGrade < 0 || numericGrade > 100) {
                return "Grade must be between 0 and 100.";
            }

            student.getStudentCourseGrades().put(course, numericGrade);
            CSVHelper.saveGrades(students);
            return "Grade assigned successfully!";
        } catch (NumberFormatException e) {
            return "Invalid grade format. Please enter a numeric value.";
        } catch (IOException e) {
            return "Error saving grade: " + e.getMessage();
        }
    }

    /**
     * Enrolls a student in a course and saves to file.
     */
    public static String enrollStudent(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            if (student.getEnrolledCourses().contains(course)) {
                return "Student is already enrolled in this course.";
            }

            if (!course.incrementEnrollment()) {
                return "Course is full. Cannot enroll student.";
            }

            student.getEnrolledCourses().add(course);

            try {
                CSVHelper.saveEnrollments(students);
                CSVHelper.saveCourses(courses);
            } catch (IOException e) {
                System.out.println("Failed to save enrollments: " + e.getMessage());
            }

            return "Student enrolled successfully in " + courseCode;
        }

        return "Enrollment failed. Please check student and course information.";
    }
}
