package com.example.ticketapi.service;

import com.example.ticketapi.model.User;
import com.example.ticketapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String documentUser) {
        return userRepository.findByDocumentNumber(documentUser);
    }

    @Override
    public User createUser(User user) {
        User userDb = getUser(user.getDocumentNumber());
        if(userDb != null) {
            log.warn("The user with document number {} is already exists", userDb.getDocumentNumber());
            return null;
        }
        userDb = userRepository.save(user);
        log.info("User with document number {} was created", userDb.getDocumentNumber());
        return userDb;
    }

    @Override
    public User updateUser(User user) {
        User userDb = getUser(user.getDocumentNumber());
        if(userDb == null) {
            log.warn("User not found");
            return null;
        }
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        log.info("User {} updated", user.getDocumentNumber());
        return userRepository.save(userDb);
    }

    @Override
    public void deleteUser(String documentUser) {
        User userDb = getUser(documentUser);
        if(userDb == null) {
            log.warn("User with document number {} not found", documentUser);
        }else{
            userRepository.delete(userDb);
            log.info("User with document number {} deleted", documentUser);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
