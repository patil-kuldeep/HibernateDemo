package school.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pta")
public class PTA {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany (mappedBy = "pta")
    private List<Parent> members;

    @ManyToMany(mappedBy = "ptas")
    private List<School> schools;

    public List<Parent> getMembers() {
        return members;
    }

    public void setMembers(List<Parent> members) {
        this.members = members;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }
}
