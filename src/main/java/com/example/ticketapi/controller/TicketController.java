package com.example.ticketapi.controller;

import com.example.ticketapi.model.Ticket;
import com.example.ticketapi.service.TicketService;
import com.example.ticketapi.utils.FormatMessageError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
@Slf4j
public class TicketController {

    private final TicketService ticketService;
    private final FormatMessageError formatMessageError;

    @Autowired
    public TicketController(TicketService ticketService, FormatMessageError formatMessageError) {
        this.ticketService = ticketService;
        this.formatMessageError = formatMessageError;
    }

    @GetMapping
    public ResponseEntity<Page<Ticket>> getAllTickets(Pageable pageable) {
        Page<Ticket> tickets = ticketService.getAllTickets(pageable);
        if(tickets.isEmpty()){
            log.warn("No tickets found");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) {
        Ticket ticket = ticketService.getTicket(id);
        if(ticket == null){
            log.warn("Ticket with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Ticket with id {} found", id);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping(value ="/create")
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket, BindingResult result) {
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessageError.formatMessage(result));
        }
        log.info("Creating ticket {}", ticket);
        Ticket newTicket = ticketService.createTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTicket);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket) {
        ticket.setId(id);
        Ticket updatedTicket = ticketService.updateTicket(ticket);
        if(updatedTicket == null){
            log.warn("Ticket id {} was not found to update", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Ticket with id {} to update was successful", id);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") Long id) {
        log.info("Deleting ticket with id {}", id);
        Ticket ticket = ticketService.getTicket(id);
        if(ticket == null){
            log.warn("Ticket with id {} was not found to delete", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
        }else{
            ticketService.deleteTicket(id);
            log.info("Ticket with id {} was deleted", id);
            return ResponseEntity.ok("Ticket deleted successfully");
        }
    }
}
