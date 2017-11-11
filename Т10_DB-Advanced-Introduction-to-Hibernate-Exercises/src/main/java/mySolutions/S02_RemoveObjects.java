package mySolutions;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class S02_RemoveObjects {
    public static void main(String[] args) {

        /*Use soft_uni database. Persist all towns from the database. Detach those whose name length is more than 5 symbols. Then transform the name of all attached towns to lowercase and save them to the database.*/

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Town> townList = em
                .createQuery("SELECT t FROM Town t WHERE length(t.name)>5")
                .getResultList();

        for (Town town : townList) {
            em.detach(town);
            town.setName(town.getName().toLowerCase());
            em.merge(town);
        }
        em.getTransaction().commit();

    }
}