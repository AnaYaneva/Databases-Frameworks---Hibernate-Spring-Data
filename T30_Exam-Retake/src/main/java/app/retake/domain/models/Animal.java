package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="age", nullable = false)
    @NotNull
    @Min(1)
    private Integer age;

    @Column(name="name", nullable = false)
    @NotNull
    @Size(min=3, max=20)
    private String name;

    @Column(name="type", nullable = false)
    @NotNull
    @Size(min=3, max=20)
    private String type;

    @OneToOne(targetEntity = Passport.class, optional = false)
    @NotNull
    //@Column(name="passport_serial_number")
    private Passport passport;

    @OneToMany(mappedBy = "animal")
    private List<Procedure> procedures;

    public Animal() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Passport getPassport() {
        return this.passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Procedure> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
