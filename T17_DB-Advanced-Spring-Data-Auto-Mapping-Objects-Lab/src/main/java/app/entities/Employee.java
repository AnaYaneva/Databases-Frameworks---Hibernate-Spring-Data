package app.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Employee{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    private String firstName;

    private String lastName;

    private BigDecimal salary;

    private Date birthDate;

    @ManyToOne
    private Address address;

    private boolean onHoliday;

    @ManyToMany
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> servants;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getServants() {
        return this.servants;
    }

    public void setServants(List<Employee> servants) {
        this.servants = servants;
    }

    public void setOnHoliday(boolean onHoliday) {
        this.onHoliday = onHoliday;
    }

    public boolean getOnHoliday(){
        return this.onHoliday;
    }
}
