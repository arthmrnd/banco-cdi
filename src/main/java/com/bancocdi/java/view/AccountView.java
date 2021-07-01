package com.bancocdi.java.view;

import com.bancocdi.java.domain.Account;

import java.util.Scanner;

public interface AccountView {

    Account createAccount(Scanner sc, String userCpf);
    void deposit(String account);
    void withdraw(String account);
    void getBalance(String account);
    boolean getAccount(String password);
}
