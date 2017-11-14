import ingredient.basic.BasicIngredient;
import ingredient.basic.Mint;
import ingredient.basic.Nettle;
import ingredient.chemical.AmmoniumChloride;
import label.BasicLabel;
import shampoo.BasicShampoo;
import shampoo.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory= Persistence
                .createEntityManagerFactory("shampoo_company");
        EntityManager em=managerFactory.createEntityManager();
        em.getTransaction().begin();

        BasicIngredient am= new AmmoniumChloride();
        BasicIngredient mint=new Mint();
        BasicIngredient nettle=new Nettle();
        em.persist(am);
        em.persist(mint);
        em.persist(nettle);

        BasicLabel label=new BasicLabel("Fresh Nuke Shampoo","Contains mint and nettle");
       //em.persist(label);
       BasicShampoo shampoo=new FreshNuke(label);

        //shampoo.getIngredients().add(mint);
        //shampoo.getIngredients().add(nettle);
        //shampoo.getIngredients().add(am);

        em.persist(shampoo);

        em.getTransaction().commit();
        em.close();
    }
}
