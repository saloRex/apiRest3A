package com.tress.app.init.user.service;

import com.tress.app.init.user.User;

import java.util.List;

public interface UserService {

    public User getUser(String username);

    public User findByEmail(String email);

    public void saveUser(User user);

    List<User> getAllUsers();
}
