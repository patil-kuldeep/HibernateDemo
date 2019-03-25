package school.model;

import javax.persistence.*;


@Table
@MappedSuperclass
@PrimaryKeyJoinColumn(name = "id")
public class Staff extends Person {
    public Staff() {
    }

    public Staff(String fName, String lName, char gender, int age) {
        super(fName, lName, gender, age);
    }
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
