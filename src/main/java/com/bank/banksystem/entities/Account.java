package com.bank.banksystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String accountNumber;

    private String name;

    private String address;

    private String contectNumber;

    private String email;

    private String balance;
    // {
    // "name":"bhautik",
    // "address":"surat",
    // "contectNumber":"1234567890",
    // "email":"bhautik@gmail.com",
    // "balance":"1000"
    // }

}
