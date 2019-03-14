package school.model;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "first_name", nullable = false, updatable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender", nullable = false)
    private char gender;
    @Column(name = "age")
    private int age;

    public Person() { }

    public Person(String fName, String lName, char gender, int age) {
        this.firstName = fName;
        this.lastName = lName;
        this.gender = gender;
        this.age = age;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
