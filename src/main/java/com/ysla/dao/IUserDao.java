package com.ysla.dao;

import com.ysla.vo.User;

import java.util.List;

public interface IUserDao {

    User getUserByUsername(String username);

    List<String> getRoleByUsername(String username);

    List<String> getPermissionByUsername(String username);
}
