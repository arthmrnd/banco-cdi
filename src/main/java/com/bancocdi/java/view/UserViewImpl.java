package com.bancocdi.java.view;

import com.bancocdi.java.Application;
import com.bancocdi.java.domain.User;
import com.bancocdi.java.services.UserService;

import javax.inject.Inject;
import java.util.Scanner;

public class UserViewImpl implements UserView {

    @Inject
    private UserService userService;

    @Override
    public User createUser(Scanner sc){
        var user = new User();
        System.out.println("Digite o nome do usuario");
        user.setName(sc.next());
        System.out.println("Digite a idade");
        user.setAge(sc.nextInt());
        if (user.getAge()<18){
            System.out.println("Precisa ter mais de 18 anos para criar uma conta");
            return null;
        }
        if (user.getAge()>113){
            System.out.printf("Coloque uma idade valida, eu sei que você não tem %d anos\n",user.getAge());
            return null;
        }
        System.out.println("Digite o seu CPF");
        user.setCpf(sc.next());
        var application = new Application();
        if (application.getUserName(user.getCpf())!=null){
            System.out.println("Este CPF ja está cadastrado");
            return null;
        }
        userService.createUser(user);
        System.out.printf("Usuario %s foi criado\n", user.getName());
        return user;
    }

    public String getUser(Scanner sc) {
        var application = new Application();
        System.out.println("Digite o seu cpf");
        String cpf = sc.next();
        return application.getUserName(cpf);
    }
}
