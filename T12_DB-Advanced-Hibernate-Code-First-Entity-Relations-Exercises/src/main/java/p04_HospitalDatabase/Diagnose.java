package p04_HospitalDatabase;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="p4_diagnoses")
public class Diagnose extends Base {

    private String name;

    @ManyToMany(targetEntity = Patient.class, mappedBy = "diagnoses")
    private Set<Patient> patients;

    public Diagnose() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Patient> getPatients() {
        return this.patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
