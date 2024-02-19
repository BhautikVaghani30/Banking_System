package com.bank.banksystem.entities;

public class Withdraw {
    private String accountNumber;
    private String withdrawAmount;

    // 547279453531
    // {
    // "accountNumber":"547279453531",
    // "withdrawAmount":"5000"
    // }
    public Withdraw(String accountNumber, String withdrawAmount) {
        this.accountNumber = accountNumber;
        this.withdrawAmount = withdrawAmount;
    }

    @Override
    public String toString() {
        return "Withdraw{" +
                "accountNumber='" + accountNumber + '\'' +
                ", withdrawAmount='" + withdrawAmount + '\'' +
                '}';
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}
