package school.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Parent")
@PrimaryKeyJoinColumn(name = "id")
public class Parent extends Person {
    @ManyToOne
    @JoinColumn(name = "pta_id")
    private PTA pta;

    public PTA getPta() {
        return pta;
    }

    public void setPta(PTA pta) {
        this.pta = pta;
    }
}
