package school.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNo")
    private String phoneNo;

    public School() {}
    public School(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }

    // one (school) to many
    @OneToMany(mappedBy = "school")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Student> students;

    @OneToMany(mappedBy = "school")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "school")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Clerk> clerks;
    // one to one
    @OneToOne
    @JoinColumn(name = "principal_id")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Principal principal;
//    // many to many
    @ManyToMany
    @JoinTable(name = "school_ptas",
            joinColumns={@JoinColumn(referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PTA> ptas;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
       return teachers;
    }
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Clerk> getClerks() {
        return clerks;
    }

    public void setClerks(List<Clerk> clerks) {
        this.clerks = clerks;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public List<PTA> getPtas() {
        return ptas;
    }

    public void setPtas(List<PTA> ptas) {
        this.ptas = ptas;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id &&
                Objects.equals(name, school.name) &&
                Objects.equals(phoneNo, school.phoneNo) &&
                Objects.equals(students, school.students) &&
                Objects.equals(teachers, school.teachers) &&
                Objects.equals(clerks, school.clerks) &&
                Objects.equals(principal, school.principal) &&
                Objects.equals(ptas, school.ptas) &&
                Objects.equals(board, school.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNo, students, teachers, clerks, principal, ptas, board);
    }
}
