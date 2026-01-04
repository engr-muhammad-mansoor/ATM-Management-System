package TechLiftProject.ATMManagementSystem.Entities;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "amount_processed")
    private Long amountProcessed;
    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

//    // Transaction entity
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id")
//    private TransactionType transactionType;








    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private Account account;
}
