package sms;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private static Set<String> courseCodes = new HashSet<>();

    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;

    public Course(String code, String name, int capacity) throws Exception {
        if (courseCodes.contains(code)) {
            throw new Exception("Course code already exists.");
        }
        this.courseCode = code;
        this.courseName = name;
        this.maxCapacity = capacity;
        this.currentEnrollment = 0;
        courseCodes.add(code);
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getCurrentEnrollment() { return currentEnrollment; }

    public boolean incrementEnrollment() {
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName;
    }

}
