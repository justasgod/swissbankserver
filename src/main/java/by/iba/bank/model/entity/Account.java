package by.iba.bank.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "bank_accounts", schema = "bank")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Integer id;

    @Column(name = "account_type", nullable = false, length = 20)
    private String accountType;

    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false, length = 10)
    private String currency;

    @Column(name = "open_date", nullable = false, length = 20)
    private String openDate;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}