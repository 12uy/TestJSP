package com.dvduy.dao;

import com.dvduy.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
    UserModel findAccount(String username, String password, Integer status);

}
