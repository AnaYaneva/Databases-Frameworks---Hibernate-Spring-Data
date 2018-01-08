package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PassportRepository passportRepository;

    @Autowired
    private ModelParser modelParser;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        if(!ValidationUtil.isValid(dto)) {
            throw new IllegalArgumentException();
        }

        Date date = new Date();
        try {
            date = sdf.parse(dto.getPassport().getRegistrationDate());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        Animal animal = new Animal();//this.modelParser.convert(dto, Animal.class);

        Passport passport=new Passport();//this.modelParser.convert(dto.getPassport(), Passport.class);
        passport.setRegistrationDate(date);
        passport.setSerialNumber(dto.getPassport().getSerialNumber());
        passport.setOwnerName(dto.getPassport().getOwnerName());
        passport.setOwnerPhoneNumber(dto.getPassport().getOwnerPhoneNumber());
        passport.setAnimal(animal);

        animal.setPassport(passport);
        animal.setAge(dto.getAge());
        animal.setName(dto.getName());
        animal.setType(dto.getType());

        if(!ValidationUtil.isValid(passport)) {
            throw new IllegalArgumentException();
        }
        this.passportRepository.save(passport);

        if(!ValidationUtil.isValid(animal)) {
            throw new IllegalArgumentException();
        }this.animalRepository.saveAndFlush(animal);
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        List<AnimalsJSONExportDTO> exportDTOs = new ArrayList<>();

        List<Passport> passports = this.passportRepository
                .findAllByOwnerPhoneNumber(phoneNumber);

        List<Animal> animalList=new ArrayList<>();

        for (Passport passport : passports) {
            animalList.add(this.animalRepository
                .findByPassportSerialNumber(passport.getSerialNumber()));
        }



        this.sortBy(animalList);

        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);


        for (Animal animal : animalList) {
            AnimalsJSONExportDTO dto=new AnimalsJSONExportDTO();

            dto.setAnimalName(animal.getName());
            dto.setOwnerName(animal.getPassport().getOwnerName());

            dto.setSerialNumber(animal.getPassport().getSerialNumber());

            dto.setAge(animal.getAge());

            dto.setRegisteredOn(dateFormat.format(animal.getPassport().getRegistrationDate()));
            exportDTOs.add(dto);
        }

        return exportDTOs;
    }

    private void sortBy(List<Animal> animals) {
        Collections.sort(animals, new Comparator<Animal>() {
            public int compare(Animal o1, Animal o2) {
                int equality = Integer.compare(o1.getAge(),o2.getAge());
                int itemsCountEquality = o1.getPassport().getSerialNumber().compareTo(o2.getPassport().getSerialNumber());
                return equality != 0 ? equality : itemsCountEquality;
            }
        });
    }
}
