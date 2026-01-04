package TechLiftProject.ATMManagementSystem.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_no")
    private Long Serial_No;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private char gender;
    @Column(name = "contact")
    private Long contact;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Account> accountList;
}
