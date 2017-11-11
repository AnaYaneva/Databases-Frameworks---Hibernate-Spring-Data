package mySolutions;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;
//import java.util.Scanner;

public class S08_GetEmployeeWithProject {
    public static void main(String[] args) throws IOException {
        /*Get an employee by id. Print only his/her first name, last name, job title and projects (only their names). The projects should be ordered by name (ascending). The output should be printed in the format given in example.*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        String input = reader.readLine();

        em.getTransaction().begin();

        Employee employee= (Employee) em
                .createQuery("SELECT e FROM Employee e WHERE e.id=?")
                .setParameter(0,Integer.valueOf(input))
                .getSingleResult();

        Set<Project> projects=employee
                .getProjects()
                .stream()
                .sorted((p1,p2)->p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toSet());
        System.out.printf("%s %s - %s\n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle());

        for (Project project : projects) {
            System.out.printf("\t%s\n",
                    project.getName());
        }

        em.getTransaction().commit();
    }
}
