package app.retake.controllers;

import app.retake.Config;
import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Controller
public class AnimalAidController {
    @Autowired
    private AnimalAidService animalAidService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private Parser jsonParser;

    public String importDataFromJSON(String jsonContent) throws IOException {
        AnimalAidJSONImportDTO[] dtos = null;

        try {
            dtos = this.jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);
        } catch (Exception e) {
            e.printStackTrace();
        }


        StringBuilder sb = new StringBuilder();

        for (AnimalAidJSONImportDTO dto : dtos) {
            try {
                this.animalAidService.create(dto);
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
