package TechLiftProject.ATMManagementSystem.Models;

import TechLiftProject.ATMManagementSystem.Entities.Account;
import TechLiftProject.ATMManagementSystem.Repositories.AccountRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
public class ModelData {


    private Long modelAccountNumber;
    private Long modelCardNumber;
    private long modelAvailableBalance;

    public ModelData(Long modelAvailableBalance) {
        this.modelAvailableBalance=modelAvailableBalance;
    }
}
