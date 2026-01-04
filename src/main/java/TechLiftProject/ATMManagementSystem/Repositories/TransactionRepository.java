package TechLiftProject.ATMManagementSystem.Repositories;

import TechLiftProject.ATMManagementSystem.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTransactionById(Long id);
    Transaction findTransactionByAccount_accountNumber(Long accountNumber);
}
