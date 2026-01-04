package TechLiftProject.ATMManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="account_type")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "max_allowed_withdrawal")
    private Long maxAllowedWithdrawal;

    @OneToOne(mappedBy = "accountType")
    private Account account;
}
