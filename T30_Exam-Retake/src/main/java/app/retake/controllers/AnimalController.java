package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private Parser jsonParser;

    public String importDataFromJSON(String jsonContent) {
        AnimalJSONImportDTO[] dtos = null;

        try {
            dtos = this.jsonParser.read(AnimalJSONImportDTO[].class, jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }


        StringBuilder sb = new StringBuilder();

        for (AnimalJSONImportDTO dto : dtos) {
            try {
                this.animalService.create(dto);
                sb.append(String.format(Config.PASSPORT,
                        dto.getName(), dto.getPassport().getSerialNumber()));
                sb.append(System.lineSeparator());
            } catch (Exception e){
                //e.printStackTrace();
                sb.append(Config.ERROR_MSG);
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) throws IOException, JAXBException {
        List<AnimalsJSONExportDTO> exportDTO =
                this.animalService.findByOwnerPhoneNumber(phoneNumber);
        return this.jsonParser.write(exportDTO);
    }
}
