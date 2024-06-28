package com.example.ticketapi.controller;

import com.example.ticketapi.model.User;
import com.example.ticketapi.service.UserService;
import com.example.ticketapi.utils.FormatMessageError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final FormatMessageError formatMessageError;

    @Autowired
    public UserController(UserService userService, FormatMessageError formatMessageError) {
        this.userService = userService;
        this.formatMessageError = formatMessageError;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
        users = userService.getAllUsers();
        if (users.isEmpty()) {
            log.warn("No users found");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping( "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        User user = userService.getUser(id);
        if (user == null) {
            log.warn("No user with document number {} found", id);
            return ResponseEntity.noContent().build();
        }
        log.info("User {} found", user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessageError.formatMessage(result));
        }
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
