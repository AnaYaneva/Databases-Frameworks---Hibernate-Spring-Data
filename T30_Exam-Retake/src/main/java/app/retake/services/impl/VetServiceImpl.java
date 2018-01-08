package app.retake.services.impl;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.domain.models.Vet;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.VetRepository;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@Service
@Transactional
public class VetServiceImpl implements VetService {
    @Autowired
    private VetRepository vetRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(VetXMLImportDTO dto) {
        if(!ValidationUtil.isValid(dto)) {
            throw new IllegalArgumentException();
        }

        Vet vet = this.modelParser.convert(dto, Vet.class);


        Set<ConstraintViolation<Vet>> constraintViolationSet = validator.validate(vet);
        if (constraintViolationSet.size() != 0) {
            throw new IllegalArgumentException();
        }
        this.vetRepository.saveAndFlush(vet);
    }
}
