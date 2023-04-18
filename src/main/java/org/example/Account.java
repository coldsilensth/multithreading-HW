package org.example;

public class Account {
    private int number;
    private int balance;

    public Account(int number, int balance) {
        this.number = number;
        this.balance = balance;
    }
    public int getNumber() {
        return number;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
