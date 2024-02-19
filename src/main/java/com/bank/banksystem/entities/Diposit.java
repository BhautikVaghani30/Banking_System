package com.bank.banksystem.entities;

public class Diposit {
    private String accountNumber;
    private String dipositAmount;

    public Diposit(String accountNumber, String dipositAmount) {
        this.accountNumber = accountNumber;
        this.dipositAmount = dipositAmount;
    }

    @Override
    public String toString() {
        return "diposit{" +
                "accountNumber='" + accountNumber + '\'' +
                ", dipositAmount='" + dipositAmount + '\'' +
                '}';
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDipositAmount() {
        return dipositAmount;
    }

    public void setDipositAmount(String dipositAmount) {
        this.dipositAmount = dipositAmount;
    }
}
