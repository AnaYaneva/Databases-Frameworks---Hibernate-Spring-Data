package app.retake.domain.dto;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "vets")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetWrapperXMLImportDTO {
    @XmlElement(name = "vet")
    private List<VetXMLImportDTO> vets;

    public VetWrapperXMLImportDTO() {
    }

    public List<VetXMLImportDTO> getVets() {
        return this.vets;
    }

    public void setVets(List<VetXMLImportDTO> vets) {
        this.vets = vets;
    }
}
