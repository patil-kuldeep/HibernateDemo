package school.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table (name = "Grade")
public class Grade {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="grade_id")
    private List<Student> students;

    private String name;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
