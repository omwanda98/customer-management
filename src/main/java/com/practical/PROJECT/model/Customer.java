package com.practical.PROJECT.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String customerId;

    @Column(nullable = false)
    private String pin;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private double balance;

    // Constructors

    public Customer(String firstName,String lastName, String email, String customerId, String pin, String accountNumber, double balance) {
        this.firstName = firstName;
        this.lastName =lastName;
        this.email = email;
        this.customerId = customerId;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }


}