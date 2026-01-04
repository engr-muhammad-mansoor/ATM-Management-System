package TechLiftProject.ATMManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "account")
@Data
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "card_number")
    private Long cardNumber;
    @Column(name="available_balance")
    private long availableBalance;
    @Column(name = "card_pin")
    private int cardPin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_type_id", referencedColumnName = "id")
    private AccountType accountType;

    @OneToOne()
    @JoinColumn(name = "card_type_id", referencedColumnName = "id")
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "customer_cnic", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList;
}

