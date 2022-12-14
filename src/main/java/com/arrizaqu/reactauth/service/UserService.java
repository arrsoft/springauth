package com.arrizaqu.reactauth.service;

import java.util.List;

import com.arrizaqu.reactauth.entity.Role;
import com.arrizaqu.reactauth.entity.User;


public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
