package TechLiftProject.ATMManagementSystem.Controllers;

import TechLiftProject.ATMManagementSystem.Services.BalanceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/atm")
public class BalanceCheckController {
    @Autowired
    BalanceCheckService balanceCheckService;
    @GetMapping(path = "/balance-inquiry")
    public String getAvailableBalance( ) {
        return (balanceCheckService.getAvailableBalance());
    }
}
