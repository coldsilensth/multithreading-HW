package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numAccounts = 5; //столько у нас счетов(количество)
        Bank bank = new Bank(numAccounts);
        Random random = new Random();

        for (int i = 0; i < numAccounts; i++) {
            Account account = new Account(i +1,100);
            bank.addAccount(account); ////добавляем сами счета в банк. на каждом счете уже есть 100 сомов
        }

        Thread[] threads = new Thread[5]; //cоздаем 5 потоков
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int account = random.nextInt(numAccounts);
                int amount = 100;
                if (random.nextBoolean()) {
                    bank.deposit(account, amount); //рандомно поток либо снимает со счета либо добавляет
                } else {
                    bank.withdraw(account, amount);
                }
            });
            threads[i].start();//запускаем поток
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("+++++++++++++++++++++");
        bank.printBalances();//список счетов и их баланс
    }
}
