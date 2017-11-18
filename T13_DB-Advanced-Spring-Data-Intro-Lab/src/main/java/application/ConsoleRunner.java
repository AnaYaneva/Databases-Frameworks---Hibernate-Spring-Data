package application;

import application.models.Account;
import application.models.User;
import application.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import application.services.account.AccountServiceImpl;
import application.services.user.UserServiceImpl;

import java.math.BigDecimal;

@Component
@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user=new User();
        user.setUsername("example6");
        user.setAge(20);

        Account account=new Account();
        account.setBalance(new BigDecimal(25000));
        account.setUser(user);

        user.getAccounts().add(account);
        userService.registerUser(user);

        //accountService.withdrawMoney(new BigDecimal(20000), account.getId());
        //accountService.transferMoney(new BigDecimal(20000),1L);
    }
}
