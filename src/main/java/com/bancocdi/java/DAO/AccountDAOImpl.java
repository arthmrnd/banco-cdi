package com.bancocdi.java.DAO;

import com.bancocdi.java.domain.Account;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class AccountDAOImpl implements AccountDAO{


    @Override
    public Account createAccount(Account account) {
        String filePath = ".\\src\\main\\java\\com\\bancocdi\\java\\data\\"
                +account.getAccountUser()+account.getAccountType()+".txt";
        try (var arq = new FileWriter(filePath, false)) {
            var writeArq = new PrintWriter(arq);
            writeArq.printf("%s%n%s%n%s%n", account.getPassword(), account.getAccountType(), account.getBalance());
        }
        catch (Exception e) {
            System.out.println("não foi possivel realizar a operação");
        }
        return account;
    }

    @Override
    public void subscribeAccount(String account, String password, String accountType, BigDecimal value) {
        String filePath = ".\\src\\main\\java\\com\\bancocdi\\java\\data\\"+account+".txt";
        try (var arq = new FileWriter(filePath, false)) {
            var writeArq = new PrintWriter(arq);
            writeArq.printf("%s%n%s%n%s%n",password,accountType,value);
        }
        catch (Exception e) {
            System.out.println("não foi possivel realizar a operação");
        }
    }
}
