package sms;

/**
 * Represents a course in the Student Management System.
 */
public class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;

    /**
     * Constructs a new course with a code, name, and maximum student capacity.
     */
    public Course(String code, String name, int capacity) {
        this.courseCode = code;
        this.courseName = name;
        this.maxCapacity = capacity;
        this.currentEnrollment = 0;
    }

    // --- Getters and Setters ---

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    /**
     * Increases the current enrollment by 1 if there's capacity.
     * @return true if enrollment succeeded, false if full.
     */
    public boolean incrementEnrollment() {
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
            return true;
        }
        return false;
    }

    /**
     * Reduces the current enrollment by 1 (e.g., if a student drops).
     */
    public void decrementEnrollment() {
        if (currentEnrollment > 0) {
            currentEnrollment--;
        }
    }

    /**
     * Override toString to display course nicely in dropdowns.
     */
    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (Capacity: " + currentEnrollment + "/" + maxCapacity + ")";
    }

    /**
     * Two courses are equal if their codes are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            Course other = (Course) obj;
            return this.courseCode.equals(other.courseCode);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }
}
