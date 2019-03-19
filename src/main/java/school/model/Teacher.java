package school.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
@PrimaryKeyJoinColumn(name = "staff_id")
public class Teacher extends Staff {

    @OneToMany (mappedBy ="teacher")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Grade> grades;

    public Teacher(){}
    public Teacher(String fName, String lName, char gender, int age) {
        super(fName, lName, gender, age);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
