package TechLiftProject.ATMManagementSystem.Repositories;

import TechLiftProject.ATMManagementSystem.Entities.AccountType;
import TechLiftProject.ATMManagementSystem.Entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    AccountType findAccountTypeById(int id);
    AccountType findAccountTypeByDescription(String description);
}
