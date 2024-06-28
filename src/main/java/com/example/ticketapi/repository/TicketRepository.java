package com.example.ticketapi.repository;

import com.example.ticketapi.model.Ticket;
import com.example.ticketapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
}
