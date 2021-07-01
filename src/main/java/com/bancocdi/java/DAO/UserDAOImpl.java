package com.bancocdi.java.DAO;

import com.bancocdi.java.domain.User;

import java.io.*;

public class UserDAOImpl implements UserDAO {
    @Override
    public User createUser(User user) {
        var filePath = ".\\src\\main\\java\\com\\bancocdi\\java\\data\\userst.txt";
        try (var arq = new FileWriter(filePath, true)) {
            var writeArq = new PrintWriter(arq);
            writeArq.printf("%s%n%d%n%s%n", user.getName(), user.getAge(), user.getCpf());
        }
        catch (Exception e) {
            System.out.println("não foi possivel realizar a operação");
        }
        return user;
    }
}
