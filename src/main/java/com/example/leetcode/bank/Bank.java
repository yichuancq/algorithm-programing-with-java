package com.example.leetcode.bank;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
class Bank {

    //账户列表
    private List<Account> accountList;

    /**
     * 使用下标从 0 开始的整数数组 balance 初始化该对象
     *
     * @param balance
     */
    public Bank(long[] balance) {
        accountList = new ArrayList<>();
        int i = 0;
        for (Long temp : balance) {
            i++;
            accountList.add(new Account(i, temp));
        }

    }

    /**
     *
     */
    private void showAccount() {
        for (Account account : accountList) {
            System.out.println(String.format("账户：%s,余额 :%s", account.getAccountId(), account.getBalance()));
        }
    }

    /**
     * 从编号为 account1 的账户向编号为 account2 的账户转帐 money 美元。如果交易成功，返回 true ，否则，返回 false
     *
     * @param account1
     * @param account2
     * @param money
     * @return
     */
    public boolean transfer(int account1, int account2, long money) {
        System.out.println(String.format("账户 ：%s->账户 ：%s ,金额:%s", account1, account2, money));
        // account1-> account2
        //转账前判断余额是否够
        if (accountList.size() < account1 && accountList.size() < account2) {
            return false;
        }
        try {
            //余额大于等于0，可以转款
            if (accountList.get(account1 - 1).getBalance() - money >= 0) {
                // 账户1转出
                Long orgBalance1 = accountList.get(account1 - 1).getBalance();
                orgBalance1 -= money;
                accountList.get(account1 - 1).setBalance(orgBalance1);
                //账户2转入
                Long orgBalance2 = accountList.get(account2 - 1).getBalance();
                orgBalance2 += money;
                accountList.get(account2 - 1).setBalance(orgBalance2);
                return true;
            }
        } catch (Exception exception) {
            return false;
        }
        return false;
    }

    /**
     * 向编号为 account 的账户存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
     *
     * @param account
     * @param money
     * @return
     */
    public boolean deposit(int account, long money) {
        System.out.println(String.format("账户 ：%s,存款金额:%s", account, money));
        if (accountList.size() < account) {
            return false;
        }
        try {
            if (money > 0) {
                Long orgBalance = accountList.get(account - 1).getBalance();
                orgBalance += money;
                accountList.get(account - 1).setBalance(orgBalance);
                return true;
            }
            return false;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 从编号为 account 的账户取款 money 美元。如果交易成功，返回 true ；否则，返回 false
     *
     * @param account
     * @param money
     * @return
     */
    public boolean withdraw(int account, long money) {
        System.out.println(String.format("账户 ：%s,取款金额:%s", account, money));
        if (accountList.size() < account) {
            return false;
        }
        try {
            Long orgBalance = accountList.get(account - 1).getBalance();
            orgBalance -= money;
            //如果取款后余额大于等于0，可以取款
            if (orgBalance >= 0) {
                accountList.get(account - 1).setBalance(orgBalance);
                return true;
            }
            return false;
        } catch (Exception exception) {
            return false;
        }
    }

    public static void main(String[] args) {

        long[] balance = {0, 100, 20, 50, 30};
        Bank bank = new Bank(balance);
        bank.showAccount();
        System.out.println(bank.withdraw(3, 10));     // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
        //  bank.showAccount();
        // 账户 3 余额为 $20 - $10 = $10 。
        System.out.println(bank.transfer(5, 1, 20));// 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
        // bank.showAccount();
        // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
        System.out.println(bank.deposit(5, 20));     // 返回 true ，可以向账户 5 存款 $20 。
        //bank.showAccount();
        // 账户 5 的余额为 $10 + $20 = $30 。
        System.out.println(bank.transfer(3, 4, 15)); // 返回 false ，账户 3 的当前余额是 $10 。
        // bank.showAccount();
        // 所以无法转账 $15 。
        // 返回 false ，交易无效，因为账户 10 并不存在。
        System.out.println(bank.withdraw(10, 50));
        // bank.showAccount();
    }
}
