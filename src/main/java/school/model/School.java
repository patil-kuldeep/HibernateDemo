package school.model;

import javax.persistence.*;
import java.util.List;

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
    // one (school) to many
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private List<Student> students;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private List<Teacher> teachers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private List<Clerk> clerks;
    // one to one
    @OneToOne
    @JoinColumn(name = "principal_id")
    private Principal principal;
//    // many to many
//    private List<PTA> ptas;
//    private Board board;

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

//    public List<PTA> getPtas() {
//        return ptas;
//    }
//
//    public void setPtas(List<PTA> ptas) {
//        this.ptas = ptas;
//    }
//
//    public Board getBoard() {
//        return board;
//    }
//
//    public void setBoard(Board board) {
//        this.board = board;
//    }
}
