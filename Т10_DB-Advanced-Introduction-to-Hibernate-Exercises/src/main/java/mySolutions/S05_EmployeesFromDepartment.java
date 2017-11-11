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

public class S05_EmployeesFromDepartment {
    public static void main(String[] args) throws IOException {
        /*Extract all employees from the Research and Development department. Order them by salary (in ascending order), then by id (in asc order). Print only their first name, last name, department name and salary. */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Employee> employees=em
                .createQuery("SELECT e FROM Employee e WHERE e.department.name='Research and Development' ORDER BY e.salary ASC, e.id ASC")
                .getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s from %s - $%.2f\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }
        em.getTransaction().commit();
    }
}
