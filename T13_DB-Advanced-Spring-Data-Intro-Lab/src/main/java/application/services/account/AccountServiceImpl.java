package application.services.account;

import application.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import application.repositories.AccountRepository;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        if (!this.accountRepository.exists(id)){
            throw new IllegalArgumentException("There is no account with this id!");
        }

        Account account=accountRepository.findOne(id);

        BigDecimal newBalance=account.getBalance().subtract(money);

        account.setBalance(newBalance);

        accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account account=accountRepository.findOne(id);

        if (account==null){
            throw new IllegalArgumentException("There is no account with this id!");
        }

        if (account.getUser()==null) {
            throw new RuntimeException("No user present for this account!");
        }

        if (money.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Mone can not be negative!");
        }

            BigDecimal newBalance=account.getBalance().add(money);

            account.setBalance(newBalance);

            accountRepository.save(account);
    }
}
