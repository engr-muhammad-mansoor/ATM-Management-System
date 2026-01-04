package TechLiftProject.ATMManagementSystem.Services;

import TechLiftProject.ATMManagementSystem.Entities.Transaction;
import TechLiftProject.ATMManagementSystem.Entities.TransactionType;
import TechLiftProject.ATMManagementSystem.Models.ModelData;
import TechLiftProject.ATMManagementSystem.Repositories.TransactionRepository;
import TechLiftProject.ATMManagementSystem.Repositories.TransactionTypeRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
@Transactional
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionTypeRepository typeRepository;
    @Autowired
    AccountDataService accountDataService;

 public void  recordTransaction(Long amount, Date latestDate,int id)
 {
     Transaction transaction=new Transaction();
     transaction.setAmountProcessed(amount);
     transaction.setAccount(accountDataService.getAccountData());
     TransactionType transactionType=typeRepository.findTransactionTypeById(id);
     transaction.setTransactionType(transactionType );
     transaction.setDate(latestDate);
     transactionRepository.save(transaction);
 }
}
