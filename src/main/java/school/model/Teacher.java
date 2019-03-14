package school.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table
@PrimaryKeyJoinColumn(name = "staff_id")
public class Teacher extends Staff {
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_staff_id")
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
