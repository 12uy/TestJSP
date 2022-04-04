package com.dvduy.service.impl;

import com.dvduy.dao.IUserDAO;
import com.dvduy.model.UserModel;
import com.dvduy.service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {

    @Inject
    private IUserDAO userDAO;



    @Override
    public UserModel findAccount(String username, String password, Integer status) {
        return userDAO.findAccount(username, password, status);
    }
}
