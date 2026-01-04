package TechLiftProject.ATMManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Data
@Entity
@Table(name="transaction_type")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;



    @OneToMany(mappedBy = "transactionType")
    private List<Transaction> transactions;


//    // TransactionType entity
//    @OneToOne(mappedBy = "transactionType")
//    private Transaction transaction;
}
