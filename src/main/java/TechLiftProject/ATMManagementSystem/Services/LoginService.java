package TechLiftProject.ATMManagementSystem.Services;


import TechLiftProject.ATMManagementSystem.Entities.Account;
import TechLiftProject.ATMManagementSystem.Models.Login;
import TechLiftProject.ATMManagementSystem.Repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Transactional
public class LoginService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountDataService accountDataService;
    Account account;
    Login login;
    public String authenticateLogin(Long modelCardNumber, Long modelCardPin) {
        account = accountRepository.findAccountByCardNumber(modelCardNumber);
//        if (account == null) {
//           return("Account not found");
//        } else if (account.getCardPin()!=modelCardPin){
//            return("Wrong Pin ");
//        }
        if (account == null || account.getCardPin()!=modelCardPin){
            return("Invalid Credentials");
        }
        else
        {
            login = Login.getInstance();
            login.setEnteredCardNumber(modelCardNumber);
            login.setEnteredCardPin(modelCardPin);
            login.setAccount(account);
            return"Welcome you are Logged in";
        }

    }
    public  void exit() {
        login.setAccount(null);

    }
}
