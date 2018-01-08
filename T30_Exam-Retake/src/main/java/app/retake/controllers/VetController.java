package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class VetController {
    @Autowired
    private VetService vetService;

    @Autowired
    @Qualifier(value = "XMLParser")
    private Parser xmlParser;

    public String importDataFromXML(String xmlContent){
        VetWrapperXMLImportDTO dtos = null;

        try {
            dtos = this.xmlParser.read(VetWrapperXMLImportDTO.class, xmlContent);
        } catch (Exception e) {
            e.printStackTrace();
        }


        StringBuilder sb = new StringBuilder();

        for (VetXMLImportDTO dto : dtos.getVets()) {
            try {
                this.vetService.create(dto);
                sb.append(String.format(Config.SUCCESS_MSG,
                        dto.getName()));
                sb.append(System.lineSeparator());
            } catch (Exception e){
                sb.append(Config.ERROR_MSG);
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
