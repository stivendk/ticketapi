package com.example.ticketapi.service;

import com.example.ticketapi.model.User;

import java.util.List;

public interface UserService {

    User getUser(String documentUser);

    User createUser(User user);

    List<User> getAllUsers();
}
