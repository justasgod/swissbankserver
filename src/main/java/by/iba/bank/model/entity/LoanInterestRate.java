package by.iba.bank.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name = "loan_interest_rates", schema = "bank")
public class LoanInterestRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number_in_stack", nullable = false)
    private Integer id;

    @Column(name = "type_of_count", nullable = false, length = 50)
    private String typeOfCount;

    @Column(name = "effective_interest_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal effectiveInterestRate;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

}