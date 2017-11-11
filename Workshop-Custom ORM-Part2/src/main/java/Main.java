import entities.Example;
import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException {
        Scanner scanner=new Scanner(System.in);

       //String username=scanner.nextLine().trim();
       //String password=scanner.nextLine().trim();
       //String db=scanner.nextLine().trim();

        Connector.createConnection("root", "Ana169072", "orm-db");
        //Connection connection=Connector.getConnection();

        //user.setId(13);
        EntityManager<User> em=new EntityManager<>(Connector.getConnection());

        User user=new User("BlaBlaBlaBla",38, new Date());
        //em.persist(user);

        //User found=em.findFirst(User.class,"age>36");
        //System.out.println(found.getUsername());
//
       // List<User> users= (List<User>) em.find(User.class,"age>31");
       // for (User user : users) {
       //     System.out.println(user.getUsername());
       // }

       //EntityManager em2=new EntityManager<>(Connector.getConnection());
//
       //Example example=new Example("first last");
       //em2.persist(example);


    }
}
