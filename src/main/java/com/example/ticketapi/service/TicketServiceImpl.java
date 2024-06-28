package com.example.ticketapi.service;

import com.example.ticketapi.enums.TicketStatus;
import com.example.ticketapi.model.Ticket;
import com.example.ticketapi.model.User;
import com.example.ticketapi.repository.TicketRepository;
import com.example.ticketapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    @Override
    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + id));
    }

    @Override
    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        User persistedUser = userRepository.findById(ticket.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        ticket.setUser(persistedUser);
        ticket.setCreateAt(LocalDateTime.now());
        ticket.setStatus(TicketStatus.ABIERTO);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Ticket ticketDb = getTicket(ticket.getId());
        if(null == ticketDb) {
            return null;
        }
        ticketDb.setStatus(ticket.getStatus());
        ticketDb.setDescription(ticket.getDescription());
        return ticketRepository.save(ticketDb);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticketDb = getTicket(id);
        if(ticketDb == null) {
            log.warn("Ticket not found with id {}", id);
        } else {
            ticketRepository.delete(ticketDb);
            log.info("Ticket with id {} deleted", id);
        }
    }

    @Override
    public List<Ticket> getTicketsByUserId(String documentNumber) {
        User user = Optional.ofNullable(userRepository.findByDocumentNumber(documentNumber)).orElseThrow(() -> new EntityNotFoundException("User not found with document number " + documentNumber)) ;
        return ticketRepository.findByUser(user);
    }
}
