package com.bancocdi.java.view;

import com.bancocdi.java.domain.Account;
import com.bancocdi.java.domain.AccountType;
import com.bancocdi.java.services.AccountService;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Scanner;

public class AccountViewImpl implements AccountView {

    @Inject
    private AccountService accountService;

    @Override
    public Account createAccount(Scanner sc, String userCpf){
        var account = new Account();
        account.setAccountUser(userCpf);
        System.out.println("Digite uma senha para a conta");
        account.setPassword(sc.next());
        System.out.println("Selecione o tipo da conta\n1 - Conta Simples\n2 - Conta Especial\n3 - Conta Poupança");

        var type = sc.nextInt();
        switch (type) {
            case 2:
                account.setAccountType(AccountType.CONTAESPECIAL);
                break;
            case 3:
                account.setAccountType(AccountType.CONTAPOUPANCA);
                break;
            case 1:
            default:
                account.setAccountType(AccountType.CONTASIMPLES);
        }

        account.setBalance(new BigDecimal("0"));
        if (account.getAccountType().equals(AccountType.CONTAESPECIAL)){
            account.setBalance(new BigDecimal("400"));
        }
        if (account.getAccountType().equals(AccountType.CONTAPOUPANCA)){
            account.setBalance(new BigDecimal("100"));
        }

        accountService.createAccount(account);
        return account;
    }

    @Override
    public void deposit(String accountType) {
        System.out.println("Qual o valor que deseja depositar?");
        var value = new Scanner(System.in).nextBigDecimal();
        accountService.deposit(accountType,value);
    }

    @Override
    public void withdraw(String account) {
        System.out.println("Qual o valor que deseja sacar?");
        var value = new Scanner(System.in).nextBigDecimal();
        accountService.withdraw(account, value);
    }

    @Override
    public void getBalance(String account) {
        String filePath = ".\\src\\main\\java\\com\\bancocdi\\java\\data\\"+account+".txt";
        try (var readArq = new BufferedReader(new FileReader(filePath))) {
            readArq.readLine();
            readArq.readLine();
            String accValue = readArq.readLine();
            System.out.println("Saldo de $" + accValue);
        }
        catch (Exception e) {
            System.out.println("não foi possivel consultar o saldo");
        }
    }

    public boolean getAccount(String password) {
        System.out.println("Digite a senha da sua conta");
        String pass = new Scanner(System.in).next();
        return accountService.accountLogin(password,pass);
    }
}
