package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {
    @Autowired
    private AnimalAidRepository animalAidRepository;

    @Autowired
    private ModelParser modelParser;

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        if(!ValidationUtil.isValid(dto)) {
            throw new IllegalArgumentException();
        }

        AnimalAid animalAid = this.modelParser.convert(dto, AnimalAid.class);
        //animalAid.setPrice(BigDecimal.valueOf(dto.getPrice()));
        AnimalAid exists=this.animalAidRepository.findByName(dto.getName());
        if (exists!=null){
            if (exists.getPrice()==animalAid.getPrice()){
                throw new IllegalArgumentException();
            }else{
                this.animalAidRepository.delete(this.animalAidRepository.findByName(dto.getName()));
                //exists.setPrice(dto.getPrice());
                //this.animalAidRepository.save(exists);
                if (!ValidationUtil.isValid(animalAid)) {
                    throw new IllegalArgumentException();
                }
                this.animalAidRepository.saveAndFlush(animalAid);

            }
        }else {
            if (!ValidationUtil.isValid(animalAid)) {
                throw new IllegalArgumentException();
            }
            this.animalAidRepository.saveAndFlush(animalAid);
        }
    }
}
