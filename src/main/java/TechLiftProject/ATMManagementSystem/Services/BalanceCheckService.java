package TechLiftProject.ATMManagementSystem.Services;


import TechLiftProject.ATMManagementSystem.Models.Login;
import TechLiftProject.ATMManagementSystem.Models.ModelData;
import TechLiftProject.ATMManagementSystem.Repositories.AccountRepository;
import TechLiftProject.ATMManagementSystem.Repositories.TransactionRepository;
import TechLiftProject.ATMManagementSystem.Repositories.TransactionTypeRepository;
import com.zaxxer.hikari.pool.HikariProxyCallableStatement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Transactional
public class BalanceCheckService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionTypeRepository typeRepository;
    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountDataService accountDataService;
    ModelData modelData;
    Date currentDate;
    Login login= Login.getInstance();

    public String getAvailableBalance() {
        accountDataService.passToAccountData();
        if(accountDataService.getAccountData()==null) {
        return("Please Login to continue");
        }
        else {

            modelData = new ModelData((accountDataService.getAccountData()).getAvailableBalance());
            currentDate=new Date();
            transactionService.recordTransaction(0L, currentDate,2);
            return ("Available Balance = " + modelData.getModelAvailableBalance());
        }
    }
}