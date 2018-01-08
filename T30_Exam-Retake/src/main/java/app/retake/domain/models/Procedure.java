package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "procedures")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_performed", nullable = false)
    @NotNull
    private Date datePerformed;

    @ManyToOne(targetEntity = Animal.class, optional = false)
    @NotNull
    private Animal animal;

    @ManyToOne(targetEntity = Vet.class, optional = false)
    @NotNull
    private Vet vet;

    @ManyToMany(targetEntity =AnimalAid.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "animal_aids_procedures",
            joinColumns = @JoinColumn(name = "procedure_id"),
            inverseJoinColumns = @JoinColumn(name = "animal_aid_id"))
    private List<AnimalAid> animalAids;

    @Transient
    private Double cost;

    public Procedure() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatePerformed() {
        return this.datePerformed;
    }

    public void setDate(Date datePerformed) {
        this.datePerformed = datePerformed;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Vet getVet() {
        return this.vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public List<AnimalAid> getAnimalAids() {
        return this.animalAids;
    }

    public void setAnimalAids(List<AnimalAid> animalAids) {
        this.animalAids = animalAids;
    }

    public Double getCost() {
        return this.cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
