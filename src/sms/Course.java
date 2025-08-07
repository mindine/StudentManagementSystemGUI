package sms;

/**
 * Represents a course in the Student Management System.
 * Each course has a unique code, name, maximum capacity, and tracks current enrollment.
 */
public class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int currentEnrollment;

    /**
     * Constructs a new Course object.
     *
     * @param code     Unique course code (e.g., CS101)
     * @param name     Course title
     * @param capacity Maximum number of students allowed
     * @throws IllegalArgumentException if code/name is null/empty or capacity is invalid
     */
    public Course(String code, String name, int capacity) {
        if (code == null || code.isEmpty() || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Course code and name must not be empty.");
        }
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }

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
        if (courseCode != null && !courseCode.trim().isEmpty()) {
            this.courseCode = courseCode;
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName != null && !courseName.trim().isEmpty()) {
            this.courseName = courseName;
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity > 0) {
            this.maxCapacity = maxCapacity;
        }
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    /**
     * Increases enrollment by 1 if the course isn't full.
     * @return true if the student was enrolled, false otherwise
     */
    public boolean incrementEnrollment() {
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
            return true;
        }
        return false;
    }

    /**
     * Decreases enrollment by 1 (useful if a student drops).
     */
    public void decrementEnrollment() {
        if (currentEnrollment > 0) {
            currentEnrollment--;
        }
    }

    /**
     * Checks if the course is full.
     * @return true if currentEnrollment >= maxCapacity
     */
    public boolean isFull() {
        return currentEnrollment >= maxCapacity;
    }

    // --- Object Overrides ---

    /**
     * Displays course details for dropdowns or logs.
     */
    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (Capacity: " + currentEnrollment + "/" + maxCapacity + ")";
    }

    /**
     * Courses are equal if they have the same course code.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;
        Course other = (Course) obj;
        return courseCode.equals(other.courseCode);
    }

    /**
     * Hashcode based on course code for map/set usage.
     */
    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }
}
