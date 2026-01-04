package TechLiftProject.ATMManagementSystem.Models;

import TechLiftProject.ATMManagementSystem.Entities.Account;
import lombok.Data;

import java.util.Date;

@Data
public class Login{
    private Long enteredCardNumber;
    private Long enteredCardPin;
    private Account account;

    private static Login instance;
    private Login() {
    }
    public static Login getInstance() {
        if(instance == null) {
            instance = new Login();
        }
        return instance;
    }


}


