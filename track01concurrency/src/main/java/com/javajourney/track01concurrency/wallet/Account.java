package com.javajourney.track01concurrency.wallet;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Account {

    private final String id;

    private BigDecimal balance;

    private History history;

    // Each account has its own lock to isolate access to the balance.
    private final Lock lock = new ReentrantLock();

    public Account(String id, BigDecimal balance) {
        this.id = id;
        this.balance = balance != null ? balance : BigDecimal.ZERO;
        this.history = new History();
    }

    public String getId() {
        return id;
    }

    // Checking the balance needs to be thread-safe if the balance changes dynamically.
    public String getBalance() {
        lock.lock();
        try {
            Locale locale = Locale.getDefault();
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
            return numberFormat.format(balance);
        } finally {
            lock.unlock();
        }
    }

    public Lock getLock() {
        return lock;
    }

    public void deposit(BigDecimal amount) {
        deposit(amount, false);
    }

    public void deposit(BigDecimal amount, boolean preventAddTransactionOnHistory) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("amount must be greater than zero");
            return;
        }

        lock.lock(); // ensure atomicity and avoid race condition
        try {
            Thread.sleep(50);

            BigDecimal oldBalance = balance;
            balance = balance.add(amount);

            if (!preventAddTransactionOnHistory) {
                history.addTransaction(oldBalance, balance, amount, OperationType.DEPOSIT, this);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(BigDecimal amount) {
        withdraw(amount, false);
    }

    public void withdraw(BigDecimal amount, boolean preventAddTransactionOnHistory) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("amount must be greater than zero");
            return;
        }

        lock.lock();
        try {
            if (balance.compareTo(amount) < 0) {
                System.out.println("Insufficient balance");
            } else {
                Thread.sleep(50);

                BigDecimal oldBalance = balance;
                balance = balance.subtract(amount);

                if (!preventAddTransactionOnHistory) {
                    history.addTransaction(oldBalance, balance, amount, OperationType.WITHDRAW, this);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    // DeadLock-proof transfer
    public void transfer(Account to, BigDecimal amount) {
        // 1. Establishes a strict locking order based on account IDs
        Account firstLock = this.id.compareTo(to.id) < 0 ? this : to;
        Account secondLock = firstLock == this ? to : this;

        // 2. Always acquire the locks in the same order, regardless of who called the method.
        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
                // 3. Executes the operationType, ensuring full atomicity of the block
                Thread.sleep(50);

                BigDecimal oldBalance = balance;
                BigDecimal toOldBalance = to.balance;

                this.withdraw(amount, true);
                to.deposit(amount, true);

                history.addTransaction(oldBalance, balance, amount, OperationType.TRANSFER_OUT, this);
                to.history.addTransaction(toOldBalance, to.balance, amount, OperationType.TRANSFER_IN, to);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                secondLock.getLock().unlock();
            }
        }  finally {
            firstLock.getLock().unlock();
        }
    }

    public ConcurrentSkipListSet<String> extract() {
        return history.getHistory();
    }

    @Override
    public String toString() {
        return STR."Account{id:'\{id}', balance:\{balance}, lock:\{lock}}";
    }
}