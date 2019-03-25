package school.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Principal principal = (Principal) o;
        return yearsOfExperience == principal.yearsOfExperience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), yearsOfExperience);
    }
}
