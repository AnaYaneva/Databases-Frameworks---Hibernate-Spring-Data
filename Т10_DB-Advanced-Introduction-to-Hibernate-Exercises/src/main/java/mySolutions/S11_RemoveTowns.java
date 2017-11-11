package mySolutions;

import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
//import java.util.Scanner;

public class S11_RemoveTowns {
    public static void main(String[] args) throws IOException {
        /*Write a program that deletes town which name is given as an input. Also delete all addresses that are in those towns. Print on the console the number addresses that were deleted as given in the example:*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        String input = reader.readLine();

        em.getTransaction().begin();

        Town town= (Town) em.createQuery("SELECT t FROM Town t WHERE t.name=?")
                .setParameter(0,input)
                .getSingleResult();

        List<Address> addresses=em.createQuery("SELECT a FROM Address a WHERE a.town.name=?")
                .setParameter(0,input)
                .getResultList();

        if (addresses.size()==1) {
            System.out.printf("%d address in %s deleted",
                    addresses.size(),
                    town.getName());
        }else {
            System.out.printf("%d addresses in %s deleted",
                    addresses.size(),
                    town.getName());
        }

        for (int i = 0; i < addresses.size(); i++) {
            Address address=addresses.get(0);
            em.remove(address);
        }

        em.remove(town);

        em.getTransaction().commit();
    }
}
