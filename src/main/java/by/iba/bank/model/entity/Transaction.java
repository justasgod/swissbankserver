package by.iba.bank.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bank_transactions", schema = "bank")
public class Transaction {
    @Id
    @Column(name = "transaction_id", nullable = false)
    private Integer id;


    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "date", nullable = false, length = 50)
    private String date;

    @Column(name = "time", nullable = false, length = 50)
    private String time;

    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @Column(name = "comment", length = 100)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}