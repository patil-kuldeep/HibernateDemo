package school.model;

import javax.persistence.*;


@Entity
@Table
@PrimaryKeyJoinColumn(name = "id")
public class Staff extends Person {
    @ManyToOne
    private School school;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
