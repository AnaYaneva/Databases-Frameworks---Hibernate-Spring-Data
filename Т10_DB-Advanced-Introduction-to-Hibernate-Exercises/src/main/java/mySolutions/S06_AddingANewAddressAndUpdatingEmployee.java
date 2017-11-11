package mySolutions;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Scanner;

public class S06_AddingANewAddressAndUpdatingEmployee {
    public static void main(String[] args) throws IOException {
        /*Create a new address with text "Vitoshka 15". Set that address to an employee with last name from user input.*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        String lastName = reader.readLine();

        em.getTransaction().begin();

        Address newAddress=new Address();
        newAddress.setText("Vitoshka 15");
        em.persist(newAddress);

        Employee found= (Employee) em
                .createQuery("SELECT e FROM Employee e WHERE e.lastName=?")
                .setParameter(0,lastName)
                .setMaxResults(1)
                .getSingleResult();

        found.setAddress(newAddress);

        em.getTransaction().commit();
    }
}
