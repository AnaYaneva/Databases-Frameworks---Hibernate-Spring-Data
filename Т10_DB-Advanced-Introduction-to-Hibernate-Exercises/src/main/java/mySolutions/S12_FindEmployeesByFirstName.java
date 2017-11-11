package mySolutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
//import java.util.Scanner;

public class S12_FindEmployeesByFirstName {
    public static void main(String[] args) throws IOException {
        /*Write a program that finds all employees whose first name starts with pattern given as an input from the console. Print their first, last name, their job title and salary in the format given in the examples below.*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        String input = reader.readLine();

        em.getTransaction().begin();
       String query= "SELECT e FROM Employee e WHERE e.firstName LIKE \'"+input+"%\'";

        List<Employee> employees=em
                .createQuery(query)
                .getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s - %s - ($%.2f)\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }

        em.getTransaction().commit();
    }
}
