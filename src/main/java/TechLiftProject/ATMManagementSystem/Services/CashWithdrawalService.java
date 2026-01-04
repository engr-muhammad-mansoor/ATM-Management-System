package TechLiftProject.ATMManagementSystem.Services;



import TechLiftProject.ATMManagementSystem.Models.Login;
import TechLiftProject.ATMManagementSystem.Models.ModelData;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Transactional
public class CashWithdrawalService {
    @Autowired
    AccountDataService accountDataService;
    @Autowired
    TransactionService transactionService;
    ModelData modelData;
    private Long dailyTransactionAmount=0L;
    Login login= Login.getInstance();
    Date currentDate;


    public String cashWithdraw(Long amount) {
            accountDataService.passToAccountData();
        if (accountDataService.getAccountData() == null) {
            return("Please Login to continue");
        }
        else if (amount>accountDataService.getAccountData().getAvailableBalance()) {
            return "Not enough funds";
        }
        else if (amount>25000) {
            return "Withdrawal Limit exceeds for one transaction";
        }
        else if(dailyTransactionAmount>50000)
        {
            return "Daily Transaction Limit exceeds";
        }
        else
        {
            accountDataService.getAccountData().setAvailableBalance(accountDataService.getAccountData().getAvailableBalance()-amount);
            accountDataService.updateAccountData();
            currentDate=new Date();
            transactionService.recordTransaction(amount, currentDate,1);
            dailyTransactionAmount=dailyTransactionAmount+amount;
            return amount+" withdrawan successfully";
        }
    }
}

