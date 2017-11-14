package p05_BillsPaymentSystem;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BasicBillingDetail {
    @Id
    private String number;
    @ManyToOne(targetEntity = User.class)
    private User owner;

    public BasicBillingDetail() {
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
