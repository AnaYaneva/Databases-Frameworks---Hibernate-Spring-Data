import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //1.
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em=factory.createEntityManager();


    }
}
