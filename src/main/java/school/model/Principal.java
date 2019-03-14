package school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table
@PrimaryKeyJoinColumn(name = "staff_id")
public class Principal extends Staff {
    @Column(name = "y_of_exp")
    private int yearsOfExperience;

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
