package com.example.leetcode.bank;

/**
 * 账户信息
 */
public class Account {
    //账户id
    private Integer accountId;
    // 账户余额
    private long balance;

    public Account(Integer accountId, long balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }
}
