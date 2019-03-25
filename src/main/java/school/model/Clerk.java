package school.model;

import javax.persistence.*;

@Entity
@Table(name = "Clerk")
@PrimaryKeyJoinColumn(name = "staff_id")
public class Clerk extends Staff {
   @Column (name = "dept")
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
