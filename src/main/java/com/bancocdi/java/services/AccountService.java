package com.bancocdi.java.services;

import com.bancocdi.java.domain.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account createAccount(Account account);
    boolean accountLogin(String password, String pass);
    void deposit(String account, BigDecimal value);
    void withdraw(String account, BigDecimal value);
}
