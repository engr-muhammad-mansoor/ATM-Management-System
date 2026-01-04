package TechLiftProject.ATMManagementSystem.Repositories;

import TechLiftProject.ATMManagementSystem.Entities.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
    CardType findCardTypeById(int id);
    CardType findCardTypeByDescription(String description);
}
