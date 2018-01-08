package app.retake.domain.models;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="vets")
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="age", nullable = false)
    @NotNull
    @Min(22)
    @Max(65)
    private Integer age;

    @Column(name="name", nullable = false)
    @NotNull
    @Size(min=3, max=40)
    private String name;

    @Column(name="phone_number", nullable = false, unique = true)
    @NotNull
    @Pattern(regexp = "(^\\+359[0-9]{9}$)|(^0[0-9]{9}$)")
    private String phoneNumber;

    @Column(name="profession", nullable = false)
    @NotNull
    @Size(min=3, max=50)
    private String profession;

    @OneToMany(mappedBy = "vet")
    private List<Procedure> procedures;

    public Vet() {
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<Procedure> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
