package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private Account[] accounts;
    public Bank(int numAccounts) {
        accounts = new Account[numAccounts];
    }
    ReentrantLock lock = new ReentrantLock();

    public void addAccount(Account account) {
        int number = account.getNumber() - 1;
        if (number >= 0 && number < accounts.length) {
            accounts[number] = account;
        } else {
            System.out.println("Номер счета выходит за пределы массива");
        }
    }

    //метод перечисления суммы на счет
    public void deposit(int number, int balance) {
        if (accounts[number] != null) {
            lock.lock();
            try {
                accounts[number].setBalance(accounts[number].getBalance() + balance);
                System.out.println("Добавлено " + balance + " на счет " + (number + 1)+ ", текущий баланс: " + accounts[number].getBalance());
            } finally {
                lock.unlock();
            }
        }
    }
    //метод который снимает деньги со счета
    public void withdraw(int number, int balance) {
        if (accounts[number] != null) {
            try {
                lock.lock();
                if (accounts[number].getBalance() >= balance) {
                    accounts[number].setBalance(accounts[number].getBalance() - balance);
                    System.out.println("Снято " + balance + " со счета " + (number + 1) + ", текущий баланс: " + accounts[number].getBalance());
                } else {
                    System.out.println("Недостаточно средств на балансе, ваш баланс " + accounts[number].getBalance() + " счет " + (number + 1));
                }
            } finally {
                lock.unlock();
            }
        }
    }

    //метод который выводит все счета и их суммы после перевода или пополнения
    public void printBalances() {
        lock.lock();
        try {
            for (int i = 0; i < accounts.length; i++) {
                Account account = accounts[i];
                if (account != null) {
                    System.out.println("Счет " + account.getNumber() + " его баланс " + account.getBalance());
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
