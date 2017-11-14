package p03_UniversitySystem.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="p3_students")
public class Student extends Person {
    private double averageGrade;
    private int attendance;

    @ManyToMany
    @JoinTable(name = "p3_students_courses",
            joinColumns =
            @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> enrolledCourses;

    public Student() {
    }

    public double getAverageGrade() {
        return this.averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return this.attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return this.enrolledCourses;
    }

    public void setCourses(Set<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
