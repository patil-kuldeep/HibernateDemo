package school.model;

import org.hibernate.annotations.Cascade;

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
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
