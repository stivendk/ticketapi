package com.example.ticketapi.service;

import com.example.ticketapi.model.Ticket;
import com.example.ticketapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    public Ticket getTicket(Long id);

    public Page<Ticket> getAllTickets(Pageable pageable);

    public Ticket createTicket(Ticket ticket);

    public Ticket updateTicket(Ticket ticket);

    public void deleteTicket(Long id);

    public List<Ticket> getTicketsByUserId(String documentNumber);
}
