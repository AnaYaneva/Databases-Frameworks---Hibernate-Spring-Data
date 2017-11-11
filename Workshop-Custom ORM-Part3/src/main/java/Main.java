import orm.Connector;
import orm.EntityManager;
import orm.EntityManagerBuilder;
import orm.strategies.DropCreateStrategy;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Scanner scanner=new Scanner(System.in);

        EntityManager e=new EntityManagerBuilder()
                .configureConnectionString()
                    .setDriver("jdbc")
                    .setAdapter("mysql")
                    .setHost("localhost")
                    .setPort("3306")
                    .setUser("root")
                    .setPass("")
                    .createConnection()
                .configureCreationType()
                    .setDropCreateStrategy()
                .setDataSource("asd")
                .build();
       //String username=scanner.nextLine().trim();
       //String password=scanner.nextLine().trim();
       //String db=scanner.nextLine().trim();
//
   //     Connector.createConnection("root", "Ana169072", "orm-db");
   //     //Connection connection=Connector.getConnection();
//
   //     //user.setId(13);
   //     EntityManager<User> em=new EntityManager<>(Connector.getConnection());
//
   //     User user=new User("BlaBlaBlaBla",38, new Date());
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
        //Connector c=new Connector(new EntityManagerBuilder());
        //Connection con=c.setDriver("jdbc")
        //        .setAdapter("mysql")
        //        .setHost("localhost")
        //        .setPort("3306")
        //        .setUser("root")
        //        .setPass("Ana169072")
        //        .setDbName("orm-db")
        //        .createConnection();
//

//        DropCreateStrategy dcs=new DropCreateStrategy("asd",e.getConnection());

    }
}
