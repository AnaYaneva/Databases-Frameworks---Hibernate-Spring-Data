package app.retake.services.impl;

import app.retake.domain.dto.ProcedureAnimalAidXMLImportDTO;
import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.ProcedureRepository;
import app.retake.repositories.VetRepository;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalAidRepository animalAidRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private ModelParser modelParser;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {

        if(!ValidationUtil.isValid(dto)) {
            throw new IllegalArgumentException();
        }

        Date date = new Date();
        try {
            date = sdf.parse(dto.getDatePerformed());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        Procedure procedure = new Procedure();//this.modelParser.convert(dto, Procedure.class);
        procedure.setDate(date);

        Vet vet=this.vetRepository.findByName(dto.getVet());
        procedure.setVet(vet);

        Animal aminal=this.animalRepository.findByPassportSerialNumber(dto.getAnimal());
        procedure.setAnimal(aminal);

        List<AnimalAid> aids=new ArrayList<>();
        for (ProcedureAnimalAidXMLImportDTO dtoo : dto.getAnimalAids()) {
            aids.add(this.animalAidRepository.findByName(dtoo.getName()));
        }
        procedure.setAnimalAids(aids);

        Set<ConstraintViolation<Procedure>> constraintViolationSet = validator.validate(procedure);
        if (constraintViolationSet.size() != 0) {
            throw new IllegalArgumentException();
        }
        this.procedureRepository.saveAndFlush(procedure);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        return null;
    }
}

