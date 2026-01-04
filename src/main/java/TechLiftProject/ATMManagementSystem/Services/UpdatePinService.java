package TechLiftProject.ATMManagementSystem.Services;

import TechLiftProject.ATMManagementSystem.Models.Login;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Transactional
public class UpdatePinService {
    @Autowired
    AccountDataService accountDataService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    LoginService loginService;
    Login login;
    Date currentDate;
    public String updatePin(int pin) {
        accountDataService.passToAccountData();
        if (accountDataService.getAccountData() == null) {
            return("Please Login to continue");
        }
        else if (pin == accountDataService.getAccountData().getCardPin())
        {
            return("Pin already exists");
        }

        else
        {
            accountDataService.getAccountData().setCardPin(pin);
            accountDataService.updateAccountData();
            currentDate=new Date();
            transactionService.recordTransaction(0L,currentDate,4);
            loginService.exit();
            return "Pin updated successfully";
        }
    }}

