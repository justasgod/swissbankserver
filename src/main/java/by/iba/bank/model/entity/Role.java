package by.iba.bank.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "bank_role", schema = "bank")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numer_of_order", nullable = false)
    private Integer id;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_user_id")
    private User user;

}