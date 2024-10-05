package by.iba.bank.model.entity;

import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "bank_users", schema = "bank")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private byte[] password;

    @Column(name = "role", nullable = false, length = 10)
    private String role;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}