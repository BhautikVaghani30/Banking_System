package com.bank.banksystem.dao;

import com.bank.banksystem.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccoutnRepository extends JpaRepository<Account, Integer> {

    Account findByAccountNumber(String accountNumber);
}
