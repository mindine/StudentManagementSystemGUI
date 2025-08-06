package sms;

import java.util.*;

public class CourseManagement {
    private static Map<String, Student> students = new HashMap<>();
    private static List<Course> courses = new ArrayList<>();

    public static Map<String, Student> getStudents() {
        return students;
    }

    public static List<Course> getCourses() {
        return courses;
    }

    public static String addStudent(String name, String email) {
        Student s = new Student(name, email);
        students.put(s.getStudentId(), s);
        return s.getStudentId();
    }

    public static String addCourse(String code, String name, int capacity) throws Exception {
        Course c = new Course(code, name, capacity);
        courses.add(c);
        return c.getCourseCode();
    }

    // Additional helper methods: updateStudent, enrollStudent, assignGrade, etc.
    public static String updateStudent(String studentID, String newName, String newEmail) {
        Student student = students.get(studentID);
        if (student != null) {
            student.setStudentName(newName);
            student.setStudentEmail(newEmail);
            return "Student ID " + studentID + " updated successfully.";
        }
        return "Student ID not found.";
    }

}
