package com.bancocdi.java.services;

import com.bancocdi.java.DAO.AccountDAO;
import com.bancocdi.java.domain.Account;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountDAO accountDAO;

    @Override
    public Account createAccount(Account account) {
        return accountDAO.createAccount(account);
    }

    public boolean accountLogin(String password, String pass) {
        if (pass.equalsIgnoreCase(password)){
            System.out.println("Login bem sucedido");
            return true;
        }
        else {
            System.out.println("Senha incorreta");
            return false;
        }
    }

    @Override
    public void deposit(String account, BigDecimal value) {
        String filePath = ".\\src\\main\\java\\com\\bancocdi\\java\\data\\"+account+".txt";
        try (var readArq = new BufferedReader(new FileReader(filePath))) {
            String password = readArq.readLine();
            String accType = readArq.readLine();
            String accValue = readArq.readLine();
            BigDecimal newValue = new BigDecimal(accValue).add(value);
            if (accType.equalsIgnoreCase("CONTAPOUPANCA")){
                newValue = new BigDecimal(accValue).multiply(new BigDecimal("0.9993"));
                newValue = newValue.add(value);
            }
            accountDAO.subscribeAccount(account,password,accType,newValue);
        }
        catch (Exception e) {
            System.out.println("não foi possivel depositar");
        }
    }

    @Override
    public void withdraw(String account, BigDecimal value) {
        String filePath = ".\\src\\main\\java\\com\\bancocdi\\java\\data\\"+account+".txt";
        try (var readArq = new BufferedReader(new FileReader(filePath))) {
            String password = readArq.readLine();
            String accType = readArq.readLine();
            String accValue = readArq.readLine();
            BigDecimal newValue = new BigDecimal(accValue).subtract(value);
            if (accType.equalsIgnoreCase("CONTAPOUPANCA")){
                newValue = new BigDecimal(accValue).multiply(new BigDecimal("0.9993"));
                newValue = newValue.subtract(value);
            }
            if (accType.equalsIgnoreCase("CONTAESPECIAL")){
                int res = newValue.compareTo(new BigDecimal("-200"));
                if (res<1){
                    System.out.println("Valor acima do seu limite de credito");
                    return;
                }
            }
            accountDAO.subscribeAccount(account,password,accType,newValue);
        }
        catch (Exception e) {
            System.out.println("não foi possivel sacar");
        }
    }
}
