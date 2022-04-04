package com.dvduy.dao.impl;

import com.dvduy.dao.IUserDAO;
import com.dvduy.mapper.UserMapper;
import com.dvduy.model.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findAccount(String username, String password, Integer status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user");
        sql.append(" INNER JOIN role ON user.roleid = role.id ");
        sql.append("Where username = ? AND password = ? AND status = ?");
        List<UserModel> user = query(sql.toString(), new UserMapper(), username, password, status);
        return user.isEmpty() ? null : user.get(0);
    }
}
