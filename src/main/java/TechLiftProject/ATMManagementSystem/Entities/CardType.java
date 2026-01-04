package TechLiftProject.ATMManagementSystem.Entities;

import lombok.Data;
import jakarta.persistence.*;
@Data
@Entity
@Table(name="card_type")
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "cardType")
    private Account account;
}
