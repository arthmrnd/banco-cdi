package com.bancocdi.java;

import com.bancocdi.java.domain.AccountType;
import com.bancocdi.java.view.AccountView;
import com.bancocdi.java.view.UserView;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Application {

    @Inject
    private UserView userView;

    @Inject
    private AccountView accountView;


    public void create(Scanner sc) {
        userView.createUser(sc);
    }

    public String getUser(Scanner sc) {
        return userView.getUser(sc);
    }

    public String getUserName(String cpf) {
        var filePath = ".\\src\\main\\java\\com\\bancocdi\\java\\data\\userst.txt";
        try (var readArq = new BufferedReader(new FileReader(filePath))) {
            String line = readArq.readLine();
            while (line != null) {
                readArq.readLine();
                line = readArq.readLine();
                if (cpf.equalsIgnoreCase(line)) {
                    return cpf;
                }
                line = readArq.readLine();
            }
        }
        catch (Exception e) {
            System.out.println("Nenhum usuario cadastrado");
        }
        return null;
    }

    public void createAccount(Scanner sc, String userCpf) {
        accountView.createAccount(sc, userCpf);
    }

    public boolean getAccount(String accountUser, AccountType accountType) {
        String filePath = ".\\src\\main\\java\\com\\bancocdi\\java\\data\\"+accountUser+accountType+".txt";
        String line;
        try (var readArq = new BufferedReader(new FileReader(filePath))) {
            line = readArq.readLine();
            return accountView.getAccount(line);

        }
        catch (Exception e) {
            System.out.println("Você não tem uma conta do tipo "+accountType);
            return false;
        }
    }

    public void accountDeposit(String account) {
        accountView.deposit(account);
    }

    public void accountWithdraw(String account) {
        accountView.withdraw(account);
    }

    public void getBalance(String account) {
        accountView.getBalance(account);
    }

}
