package TechLiftProject.ATMManagementSystem.Repositories;

import TechLiftProject.ATMManagementSystem.Entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {
    TransactionType findTransactionTypeById(int id);
    TransactionType findTransactionTypeByDescription(String description);
}