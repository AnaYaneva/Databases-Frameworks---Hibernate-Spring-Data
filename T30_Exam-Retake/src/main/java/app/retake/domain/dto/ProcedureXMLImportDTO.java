package app.retake.domain.dto;

import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Vet;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLImportDTO {

    @XmlElement(name="vet")
    @NotNull
    @Size(min=3, max=40)
    private String vet;

    @XmlElement(name="animal")
    @NotNull
    @Size(min=3, max=20)
    private String animal;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLImportDTO> animalAids;

    @XmlElement(name="date")
    @NotNull
    private String datePerformed;

    public ProcedureXMLImportDTO() {
    }

    public String getVet() {
        return this.vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getAnimal() {
        return this.animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public List<ProcedureAnimalAidXMLImportDTO> getAnimalAids() {
        return this.animalAids;
    }

    public void setAnimalAids(List<ProcedureAnimalAidXMLImportDTO> animalAids) {
        this.animalAids = animalAids;
    }

    public String getDatePerformed() {
        return this.datePerformed;
    }

    public void setDatePerformed(String datePerformed) {
        this.datePerformed = datePerformed;
    }
}
