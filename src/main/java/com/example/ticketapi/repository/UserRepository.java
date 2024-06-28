package com.example.ticketapi.repository;

import com.example.ticketapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
   User findByDocumentNumber(String documentNumber);
}
