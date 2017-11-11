package mySolutions;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
//import java.util.Scanner;

public class S10_IncreaseSalaries {
    public static void main(String[] args) throws IOException {
        /*Write a program that increases salaries of all employees that are in the Engineering, Tool Design, Marketing or Information Services department by 12%. Then print first name, last name and salary for those employees whose salary was increased.*/

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Employee> employees=em
                .createQuery("SELECT e FROM Employee e WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')")
                .getResultList();

        for (Employee employee : employees) {
            BigDecimal newSalary= employee.getSalary().multiply(new BigDecimal(1.12));
            employee.setSalary(newSalary);
            em.merge(employee);
        }

        for (Employee employee : employees) {
            System.out.printf("%s %s ($%.2f)\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary());
        }
        em.getTransaction().commit();
    }
}
