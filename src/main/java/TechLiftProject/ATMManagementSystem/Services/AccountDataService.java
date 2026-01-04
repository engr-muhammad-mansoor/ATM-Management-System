package TechLiftProject.ATMManagementSystem.Services;

import TechLiftProject.ATMManagementSystem.Entities.Account;
import TechLiftProject.ATMManagementSystem.Models.Login;
import TechLiftProject.ATMManagementSystem.Repositories.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Data
@Transactional
public class AccountDataService {

    Login login=Login.getInstance();
     Account accountData=null;
     @Autowired
    AccountRepository accountRepository;
    public void passToAccountData()
    {
        accountData = login.getAccount();
    }
    public void updateAccountData()
    {
        accountRepository.save(accountData);
    }


}





