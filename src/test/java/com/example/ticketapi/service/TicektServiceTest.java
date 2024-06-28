package com.example.ticketapi.service;

import com.example.ticketapi.enums.TicketStatus;
import com.example.ticketapi.model.Ticket;
import com.example.ticketapi.model.User;
import com.example.ticketapi.repository.TicketRepository;
import com.example.ticketapi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TicektServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;


    private TicketService ticketService;

    private List<Ticket> allTickets;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        User esteban = User.builder()
                .documentNumber("1234566")
                .firstName("Esteban")
                .lastName("Morales")
                .build();

        Ticket ticket1 = Ticket.builder()
                .id(123L)
                .status(TicketStatus.ABIERTO)
                .createAt(LocalDateTime.now())
                .user(esteban)
                .description("Esse et sequi iste eos.")
                .build();

        Ticket ticket2 = Ticket.builder()
                .id(456L)
                .status(TicketStatus.CERRADO)
                .createAt(LocalDateTime.of(2024,5,10,10,0))
                .updateAt(LocalDateTime.now())
                .user(esteban)
                .description("Esse et sequi iste eos4.")
                .build();

        Ticket ticket3 = Ticket.builder()
                .id(627L)
                .status(TicketStatus.ABIERTO)
                .createAt(LocalDateTime.now())
                .user(esteban)
                .description("Esse et sequi iste eos2.")
                .build();

        Long ticketId = 123L;

        List<Ticket> ticketsByEsteban = new ArrayList<>();
        ticketsByEsteban.add(ticket1);
        ticketsByEsteban.add(ticket2);

        this.allTickets = new ArrayList<>();
        allTickets.add(ticket1);
        allTickets.add(ticket2);
        allTickets.add(ticket3);


        Mockito.when(userRepository.save(esteban)).thenReturn(esteban);
        Mockito.when(userRepository.findByDocumentNumber(esteban.getDocumentNumber())).thenReturn(esteban);
        Mockito.when(ticketRepository.save(ticket1)).thenReturn(ticket1);
        Mockito.when(ticketRepository.save(ticket2)).thenReturn(ticket2);
        Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket1));
        Mockito.when(ticketRepository.findByUser(esteban)).thenReturn(ticketsByEsteban);

        ticketService = new TicketServiceImpl(ticketRepository, userRepository);
    }

    @Test
    public void whenValidGetTicket_thenReturnTicket() {
        Ticket ticketFound = ticketService.getTicket(123L);
        Assertions.assertEquals(TicketStatus.ABIERTO, ticketFound.getStatus());
    }

    @Test
    public void whenInvalidGetTicket_thenReturnException() {
        Assertions.assertThrows(EntityNotFoundException.class, () ->{
            ticketService.getTicket(124L);
        });
    }

    @Test
    public void whenGetTicketsByUser_thenReturnTickets() {
        List<Ticket> ticketsFound = ticketService.getTicketsByUserId("1234566");
        Assertions.assertEquals(2, ticketsFound.size());
    }

    @Test
    public void whenGetTicketsByInvalidUser_thenReturnException() {
        Assertions.assertThrows(EntityNotFoundException.class, () ->{
            ticketService.getTicketsByUserId("123454");
        });
    }

    @Test
    public void whenValidGetAllTickets_thenReturnTickets() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Ticket> ticketPage = new PageImpl<>(allTickets, pageable, allTickets.size());

        Mockito.when(ticketRepository.findAll(pageable)).thenReturn(ticketPage);
        Page<Ticket> ticketsFound = ticketService.getAllTickets(pageable);

        Assertions.assertEquals(3, ticketsFound.getTotalElements());
        Assertions.assertEquals(1, ticketsFound.getTotalPages());
        Assertions.assertEquals(allTickets, ticketsFound.getContent());
    }

}
