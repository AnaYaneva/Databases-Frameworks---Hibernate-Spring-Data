package p05_BillsPaymentSystem;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="p5_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "owner")
    private Set<BasicBillingDetail> billingDetail;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<BasicBillingDetail> getBillingDetail() {
        return this.billingDetail;
    }

    public void setBillingDetail(Set<BasicBillingDetail> billingDetail) {
        this.billingDetail = billingDetail;
    }
}
