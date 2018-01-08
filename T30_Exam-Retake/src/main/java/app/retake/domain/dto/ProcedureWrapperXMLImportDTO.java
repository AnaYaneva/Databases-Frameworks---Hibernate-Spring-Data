package app.retake.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "procedures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureWrapperXMLImportDTO {
    @XmlElement(name = "procedure")
    private List<ProcedureXMLImportDTO> procedure;

    public ProcedureWrapperXMLImportDTO() {
    }

    public List<ProcedureXMLImportDTO> getProcedure() {
        return this.procedure;
    }

    public void setProcedure(List<ProcedureXMLImportDTO> procedure) {
        this.procedure = procedure;
    }
}
