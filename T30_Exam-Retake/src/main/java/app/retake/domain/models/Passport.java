package app.retake.domain.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="passports")
public class Passport implements Serializable {
    @Id
    @Column(name="serial_number")
    @Pattern(regexp = "^[A-Za-z]{7}[0-9]{3}$")
    private String serialNumber;

    @Column(name="owner_name", nullable = false)
    @NotNull
    @Size(min=3, max=30)
    private String ownerName;

    @Column(name="owner_phone_number", nullable = false)
    @NotNull
    @Pattern(regexp = "(^\\+359[0-9]{9}$)|(^0[0-9]{9}$)")
    private String ownerPhoneNumber;

    @Column(name="registered_on", nullable = false)
    @NotNull
    private Date registrationDate;

    @OneToOne(mappedBy = "passport", optional = false)
    @Transient
    private Animal animal;

    public Passport() {
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return this.ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
