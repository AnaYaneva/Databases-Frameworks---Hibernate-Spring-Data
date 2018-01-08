package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.*;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ProcedureController {
    @Autowired
    private ProcedureService procedureService;

    @Autowired
    @Qualifier(value = "XMLParser")
    private Parser xmlParser;

    public String importDataFromXML(String xmlContent){
        ProcedureWrapperXMLImportDTO dtos = null;

        try {
            dtos = this.xmlParser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
        } catch (Exception e) {
            e.printStackTrace();
        }


        StringBuilder sb = new StringBuilder();

        for (ProcedureXMLImportDTO dto : dtos.getProcedure()) {
            try {
                this.procedureService.create(dto);
                sb.append(String.format("Record successfully imported."));
                sb.append(System.lineSeparator());
            } catch (Exception e){
                sb.append(Config.ERROR_MSG);
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        return null;
    }
}
