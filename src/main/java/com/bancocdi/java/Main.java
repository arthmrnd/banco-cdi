package com.bancocdi.java;

import com.bancocdi.java.domain.AccountType;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        final WeldContainer container = new Weld().initialize();
        final Application application = container.select(Application.class).get();
        start(application);
        //TODO GET RIDE OF THE TRASH ON THE CODE
        //TODO ENHANCE THE ENTIRE APPLICATION
        
    }

    private static void start(Application application) throws IOException {
        int option;
        var sc = new Scanner(System.in);
        System.out.println("\tBem-vindo ao banco!");
        do {
            System.out.println("\nDigite a opção desejada\n1 - Entrar com o seu usuario" +
                    "\n2 - Cadastrar Usuario\n0 - Sair");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    login(application);
                    break;
                case 2:
                    application.create(sc);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }while (option>0);
    }

    private static void login(Application application) throws IOException {
        var sc = new Scanner(System.in);
        var user = application.getUser(sc);
        if (user == null){
            System.out.println("Usuario não encontrado");
        }
        else {
            userLogged(application, user);
        }
    }

    private static void userLogged(Application application, String userCpf) throws IOException {
        System.out.println("\n\tBem vindo!");
        int opt;
        var sc = new Scanner(System.in);
        do {
            System.out.println("\nDigite a opção desejada\n1 - Entrar na sua conta" +
                    "\n2 - Cadastrar uma conta\n0 - Sair");
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    String account = accountLogin(userCpf, application);
                    if (account!=null){
                        accountLogged(application, account);
                    }
                    break;
                case 2:
                    application.createAccount(sc, userCpf);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }while (opt>0);

    }


    private static String accountLogin(String userCpf, Application application) throws IOException {
        System.out.println("Selecione o tipo da conta\n1 - Conta Simples\n2 - Conta Especial\n3 - Conta Poupança");
        var sc = new Scanner(System.in);
        var type = sc.nextInt();
        switch (type) {
            case 2:
                if(application.getAccount(userCpf, AccountType.CONTAESPECIAL)){
                    return userCpf+AccountType.CONTAESPECIAL;
                }
                break;
            case 3:
                if(application.getAccount(userCpf, AccountType.CONTAPOUPANCA)){
                    return userCpf+AccountType.CONTAPOUPANCA;
                }
                break;
            case 1:
            default:
                if(application.getAccount(userCpf, AccountType.CONTASIMPLES)){
                    return userCpf+AccountType.CONTASIMPLES;
                }
                break;
        }
        return null;
    }

    private static void accountLogged(Application application, String account) {
        int op;
        do {
            System.out.println("\nQual operação deseja realizar?");
            System.out.println("\n1 - Depositar\n2 - Sacar\n3 - Consultar o saldo\n0 - Sair");
            op = new Scanner(System.in).nextInt();
            switch (op) {
                case 0:
                    break;
                case 1:
                    application.accountDeposit(account);
                    break;
                case 2:
                    application.accountWithdraw(account);
                    break;
                case 3:
                    application.getBalance(account);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (op>0);
    }
}
