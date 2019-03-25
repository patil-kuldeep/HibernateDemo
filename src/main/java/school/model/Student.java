package school.model;

import javax.persistence.*;
import java.util.Objects;

/*
* This class defines the Student.
* It contains the attributes to identify a student.
*/

@Entity
@Table(name = "Student")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends Person {

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "rollNo")
    private int rollNo;

    @ManyToOne
    @JoinColumn(name="grade_id")
    private Grade grade;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rollNo == student.rollNo &&
                Objects.equals(school, student.school) &&
                Objects.equals(grade, student.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(school, rollNo, grade);
    }
}
