package com.bancocdi.java.services;

import com.bancocdi.java.DAO.UserDAO;
import com.bancocdi.java.domain.User;

import javax.inject.Inject;

public class UserServiceImpl implements UserService{

    @Inject
    private UserDAO userDAO;

    @Override
    public User createUser(User user) {
        return userDAO.createUser(user);
    }
}
