package mySolutions;

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
//import java.util.Scanner;

public class S07_AddressesWithEmployeeCount {
    public static void main(String[] args) throws IOException {
        /*Find all addresses, ordered by the number of employees who live there (descending), then by town id (ascending). Take only the first 10 addresses and print their address text, town name and employee count. */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Address> addresses=em
                .createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC, a.town.id ASC")
                .setMaxResults(10)
                .getResultList();

        for (Address address : addresses) {
            System.out.printf("%s, %s - %d employees\n",
                    address.getText(),
                    address.getTown().getName(),
                    address.getEmployees().size());
        }



        em.getTransaction().commit();
    }
}
