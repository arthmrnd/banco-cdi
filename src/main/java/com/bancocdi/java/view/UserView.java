package com.bancocdi.java.view;

import com.bancocdi.java.domain.User;

import java.util.Scanner;

public interface UserView {
    User createUser(Scanner sc);
    String getUser(Scanner sc);
}
