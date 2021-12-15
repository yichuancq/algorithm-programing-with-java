package com.example.leetcode.bank;

public class MyBank {

    long[] balance;

    public MyBank(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!(account1 >= 1 && account1 <= balance.length && account2 >= 1 && account2 <= balance.length)) {
            return false;
        }
        account1--;
        account2--;
        if (balance[account1] >= money) {
            balance[account1] -= money;
            balance[account2] += money;
            return true;
        }
        return false;
    }

    public boolean deposit(int account, long money) {
        if (!(account >= 1 && account <= balance.length)) {
            return false;
        }
        account--;
        balance[account] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (!(account >= 1 && account <= balance.length)) {
            return false;
        }
        account--;
        if (balance[account] >= money) {
            balance[account] -= money;
            return true;
        }
        return false;
    }
}

