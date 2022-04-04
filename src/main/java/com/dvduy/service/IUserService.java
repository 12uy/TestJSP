package com.dvduy.service;

import com.dvduy.model.UserModel;

public interface IUserService {
    UserModel findAccount(String username, String password, Integer status);
}
