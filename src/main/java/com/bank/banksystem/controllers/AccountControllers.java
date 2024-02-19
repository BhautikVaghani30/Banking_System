package com.bank.banksystem.controllers;

import com.bank.banksystem.Constants.MSG;
import com.bank.banksystem.entities.Account;
import com.bank.banksystem.entities.Diposit;
import com.bank.banksystem.entities.MoneyTransfer;
import com.bank.banksystem.entities.Withdraw;
import com.bank.banksystem.utilites.bankUtils;
import com.bank.banksystem.servies.AccountServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccountControllers {
    @Autowired
    AccountServies accountServies;

    @PostMapping("openAccount")
    public ResponseEntity<String> openAccount(@RequestBody Account account) {
        try {
            return accountServies.openAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("Withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestBody Withdraw withdrawAmount) {
        try {
            return accountServies.withdrawMoney(withdrawAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("deposit")
    public ResponseEntity<String> depositAmount(@RequestBody Diposit depositAmount) {
        try {
            System.out.println(depositAmount);
            return accountServies.depositAmount(depositAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("getBalance")
    public ResponseEntity<String> getBalance(@RequestBody(required = true) Map<String, String> accountNumber) {
        try {
            System.out.println(accountNumber);
            return accountServies.getBalance(accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("transfer")
    public ResponseEntity<String> moneyTransfer(@RequestBody MoneyTransfer moneyTransfer) {
        try {
            return accountServies.moneyTransfer(moneyTransfer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankUtils.getResponseEntity(MSG.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
