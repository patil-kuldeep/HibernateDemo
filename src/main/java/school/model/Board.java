package school.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    // one to many
    @OneToMany(mappedBy = "board")
    private List<School> schools;

    @Column(name = "board_name")
    private String name;

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

}
