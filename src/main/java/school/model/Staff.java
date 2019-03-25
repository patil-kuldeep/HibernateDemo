package school.model;

import javax.persistence.*;


@Table
@MappedSuperclass
@PrimaryKeyJoinColumn(name = "id")
public class Staff extends Person {
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
