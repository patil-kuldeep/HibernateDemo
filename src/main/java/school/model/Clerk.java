package school.model;

import javax.persistence.*;

@Entity
@Table(name = "Clerk")
@PrimaryKeyJoinColumn(name = "staff_id")
public class Clerk extends Staff {
   @Column (name = "dept")
    private String department;

   public Clerk() {}
    public Clerk(String fName, String lName, char gender, int age, String department) {
        super(fName, lName, gender, age);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
