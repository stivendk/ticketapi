package com.example.ticketapi.service;

import com.example.ticketapi.model.User;

import java.util.List;

public interface UserService {

    public User getUser(String documentUser);

    public User createUser(User user);

    public List<User> getUsersBy(User user);

    public void deleteUser(String documentUser);

    public List<User> getAllUsers();
}
