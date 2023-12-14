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
@Table(name = "loans", schema = "bank")
public class Loan {
    @Id
    @Column(name = "loan_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "term", nullable = false)
    private Integer term;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "monthly_payment", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyPayment;

    @Column(name = "issue_date", nullable = false, length = 50)
    private String issueDate;

    @OneToMany
    private Set<LoanInterestRate> loanInterestRates = new LinkedHashSet<>();

}