package com.example.ticketapi.repository;

import com.example.ticketapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   public User findByDocumentNumber(String documentNumber);

   public List<User> findUserByFirstName(String firstName);

   public List<User> findUserByLastName(String lastName);
}
