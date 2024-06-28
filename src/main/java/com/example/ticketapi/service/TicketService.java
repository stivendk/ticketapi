package com.example.ticketapi.service;

import com.example.ticketapi.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

     Ticket getTicket(Long id);

     Page<Ticket> getAllTickets(Pageable pageable);

     Ticket createTicket(Ticket ticket);

     Ticket updateTicket(Ticket ticket);

     void deleteTicket(Long id);

     List<Ticket> getTicketsByUserId(String documentNumber);
}
