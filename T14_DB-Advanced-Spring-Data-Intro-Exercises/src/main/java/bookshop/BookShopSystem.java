package bookshop;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookShopSystem {
    public static void main(String[] args) {
       //SpringApplication app=new SpringApplication(BookShopSystem.class);
       //app.setBannerMode(Banner.Mode.OFF);
       //app.run(args);
        SpringApplication.run(BookShopSystem.class,args);
    }
}
