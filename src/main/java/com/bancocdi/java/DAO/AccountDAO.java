package com.bancocdi.java.DAO;

import com.bancocdi.java.domain.Account;

import java.math.BigDecimal;

public interface AccountDAO {
    Account createAccount(Account account);
    public void subscribeAccount(String account, String password, String accountType, BigDecimal value);
}
