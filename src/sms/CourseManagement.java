package sms;

import java.io.IOException;
import java.util.*;

/**
 * Manages all core operations related to students, courses, enrollments, and grades.
 * This class acts as the logic layer connecting the GUI with data persistence.
 */
public class CourseManagement {

    // Internal maps to store all student and course records by their ID/code
    private static Map<String, Student> students = new HashMap<>();
    private static Map<String, Course> courses = new HashMap<>();

    // Static block to load data on application start
    static {
        try {
            CSVHelper.loadAllData(students, courses);
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    /**
     * Retrieves the map of all students.
     * @return Map of student ID to Student object.
     */
    public static Map<String, Student> getStudents() {
        return students;
    }

    /**
     * Retrieves all courses as a collection.
     * @return Collection of Course objects.
     */
    public static Collection<Course> getCourses() {
        return courses.values();
    }

    /**
     * Adds a new student to the system and saves the updated data.
     * @param name  Student's full name
     * @param email Student's email
     * @return The generated student ID
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
     * Adds a new course to the system and saves the updated data.
     * @param code     Course code (e.g., CS101)
     * @param name     Course name
     * @param capacity Maximum number of enrollments
     * @return Course code (as confirmation)
     * @throws Exception if creation fails (e.g., invalid data)
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
     * Updates a student's name and email.
     * @param studentID  ID of the student to update
     * @param newName    New name
     * @param newEmail   New email
     * @return Confirmation message
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
     * Assigns a numeric grade to a student for a specific course.
     * Ensures the grade is valid and saves the updated records.
     *
     * @param studentId  ID of the student
     * @param courseCode Code of the course
     * @param grade      Grade (as string input from UI)
     * @return Status message to be displayed in the UI
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
     * Enrolls a student in a course if they are not already enrolled and space is available.
     *
     * @param studentId  ID of the student
     * @param courseCode Code of the course
     * @return Status message describing the outcome
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
