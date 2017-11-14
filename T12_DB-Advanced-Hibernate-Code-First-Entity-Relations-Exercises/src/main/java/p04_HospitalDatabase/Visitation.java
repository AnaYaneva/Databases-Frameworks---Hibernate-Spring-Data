package p04_HospitalDatabase;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="p4_visitations")
public class Visitation extends Base {

    private Date date;

    @ManyToOne(targetEntity = Patient.class)
    private Patient patient;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
