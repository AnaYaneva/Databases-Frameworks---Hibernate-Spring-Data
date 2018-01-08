package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.api.ConsoleIO;
import app.retake.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private ConsoleIO consoleIO;
    private FileIO fileIOUtil;

    private AnimalAidController animalAidController;
    private AnimalController animalController;
    private VetController vetController;
    private ProcedureController procedureController;


    @Autowired
    public Terminal(ConsoleIO consoleIO, FileIO fileIOUtil, AnimalAidController animalAidController, AnimalController animalController, VetController vetController, ProcedureController procedureController) {
        this.consoleIO = consoleIO;
        this.fileIOUtil = fileIOUtil;
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.procedureController = procedureController;
    }

    @Override
    public void run(String... strings) throws Exception {
        //JSON Import
       this.consoleIO.write(this.animalAidController.importDataFromJSON(this.fileIOUtil.read(Config.ANIMAL_AIDS_IMPORT_JSON)));
       this.consoleIO.write(this.animalController.importDataFromJSON(this.fileIOUtil.read(Config.ANIMALS_IMPORT_JSON)));

       //XML Import
        this.consoleIO.write(this.vetController.importDataFromXML(this.fileIOUtil.read(Config.VETS_IMPORT_XML)));
        this.consoleIO.write(this.procedureController.importDataFromXML(this.fileIOUtil.read(Config.PROCEDURES_IMPORT_XML)));
        //export
        this.consoleIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"));
    }
}
