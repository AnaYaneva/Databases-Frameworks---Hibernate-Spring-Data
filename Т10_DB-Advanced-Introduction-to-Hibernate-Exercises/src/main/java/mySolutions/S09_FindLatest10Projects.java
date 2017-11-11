package mySolutions;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
//import java.util.Scanner;

public class S09_FindLatest10Projects {
    public static void main(String[] args) throws IOException {
        /*Write a program that prints last 10 started projects. Print their name, description, start and end date and sort them by name lexicographically. See example for output format.*/

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Project> projects=em
                .createQuery("SELECT p FROM Project p ORDER BY p.startDate DESC")
                .setMaxResults(10)
                .getResultList();

        for (Project project : projects) {
            System.out.printf("Project name: %s\n" +
                    " \tProject Description: %s\n" +
                    " \tProject Start Date:%s\n" +
                    " \tProject End Date: %s\n",
                    project.getName(),
                    project.getDescription(),
                    project.getStartDate(),
                    project.getEndDate());
        }
        em.getTransaction().commit();
    }
}
