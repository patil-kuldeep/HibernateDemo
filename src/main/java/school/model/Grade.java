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
    @JoinColumn(name = "teacher_staff_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
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
