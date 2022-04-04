package com.dvduy.mapper;

import com.dvduy.model.RoleModel;
import com.dvduy.model.UserModel;

import java.sql.ResultSet;

public class UserMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet rs) {
        UserModel user = new UserModel();
        try {
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("fullname"));
            user.setStatus(rs.getInt("status"));
            user.setRoleId(rs.getLong("roleid"));
            try {
                RoleModel role = new RoleModel();
                role.setCode(rs.getString("code"));
                role.setName(rs.getString("name"));
                user.setRole(role);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
