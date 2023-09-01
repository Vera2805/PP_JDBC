package jm.task.core.jdbc.model;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Column;
@Entity
@Table(name = "users")
public class User {
    public String setName;
    public String setLastName;
    public Byte setAge;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + setName + '\'' +
                ", lastName='" + setLastName + '\'' +
                ", age=" + setAge +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        System.out.println(" TEST7 user " + this.lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return setName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return setLastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return setAge;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

}
