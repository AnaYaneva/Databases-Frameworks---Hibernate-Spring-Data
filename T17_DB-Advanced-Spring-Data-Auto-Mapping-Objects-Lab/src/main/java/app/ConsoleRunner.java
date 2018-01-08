package app;

import app.dtos.EmployeeDto;
import app.entities.Address;
import app.entities.Employee;
import app.repositories.AddressRepository;
import app.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private AddressRepository addressRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public ConsoleRunner(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
       //Address address = new Address();

       //address.setCity("Sofia");
       //address.setCountry("Bulgaria");


       //addressRepository.save(address);

       // Employee one=this.employeeRepository.findOne(1L);
//
       // ModelMapper modelMapper=new ModelMapper();
       // EmployeeDto dto=modelMapper.map(one, EmployeeDto.class);
       // System.out.println(dto.getFirstName());
    }
}
