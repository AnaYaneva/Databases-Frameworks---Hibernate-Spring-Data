package mySolutions;

import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
//import java.util.Scanner;

public class S03_ContainsEmployee {
    public static void main(String[] args) throws IOException {
        /*Use soft_uni database. Write a program that check if given employee name as an input is contained in the database.*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        String[] input = reader.readLine().split("\\s+");
        String firstName=input[0].trim();
        String lastName=input[1].trim();

        em.getTransaction().begin();

        List<Employee> result=em
                .createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE ? AND e.lastName LIKE ?")
                .setParameter(0,firstName)
                .setParameter(1,lastName)
                .getResultList();

        if (result.size()>=1){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

        em.getTransaction().commit();
    }
}
