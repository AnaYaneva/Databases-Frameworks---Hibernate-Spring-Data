package p04_HospitalDatabase;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="p4_patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Date dateOfBirth;
    private String picture;
    private boolean hasMedicalInsurance;

    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;
    @ManyToMany
    @JoinTable(name="p4_diagnoses_patients",
            joinColumns = @JoinColumn(name="patient_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="diagnose_id", referencedColumnName = "id"))
    private Set<Diagnose> diagnoses;
    @ManyToMany
    @JoinTable(name="p4_medications_patients",
            joinColumns = @JoinColumn(name="patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="medication_id", referencedColumnName = "id"))
    private Set<Medication> medications;

    public Patient() {
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set<Visitation> getVisitations() {
        return this.visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    public Set<Diagnose> getDiagnoses() {
        return this.diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Medication> getMedications() {
        return this.medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public boolean getHasMedicalInsurance() {
        return this.hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }
}
