package TechLiftProject.ATMManagementSystem.Controllers;

import TechLiftProject.ATMManagementSystem.Services.CashWithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/atm")
public class CashWithdrawalController {
    @Autowired
    CashWithdrawalService cashWithdrawService;
    @PutMapping(path="/withdraw-cash/{amount}")
    public String cashWithdrawal(@PathVariable("amount")  Long amountToWithdraw)
    {
        return cashWithdrawService.cashWithdraw(amountToWithdraw);
    }
}
