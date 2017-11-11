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

public class S04_EmployeesWithSalaryOver50000 {
    public static void main(String[] args) throws IOException {
        /*Write a program to get the first name of all employees who have salary over 50 000.*/

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Employee> employees=em.createQuery("SELECT e FROM Employee e WHERE e.salary>50000")
                .getResultList();

        for (Employee employee : employees) {
            System.out.println(employee.getFirstName());
        }

        em.getTransaction().commit();
    }
}
