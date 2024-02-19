package com.bank.banksystem.entities;

public class MoneyTransfer {
    private String from;
    private String to;
    private String amount;
    // {
    // "from":"",
    // "to":"",
    // "amount":""
    // }

    public MoneyTransfer(String from, String to, String amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MoneyTransfer{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
