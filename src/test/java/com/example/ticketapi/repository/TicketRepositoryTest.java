package com.example.ticketapi.repository;

import com.example.ticketapi.enums.TicketStatus;
import com.example.ticketapi.model.Ticket;
import com.example.ticketapi.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUser_thenReturnListTicket() {
        User user01 = User.builder()
                .documentNumber("123456")
                .firstName("Alberto")
                .lastName("Carros")
                .build();
        userRepository.save(user01);
        Ticket ticket01 = Ticket.builder()
                .status(TicketStatus.valueOf("ABIERTO"))
                .createAt(LocalDateTime.now())
                .description("Esse et sequi iste eos.")
                .user(user01)
                .build();
        ticketRepository.save(ticket01);

        List<Ticket> ticketFounds = ticketRepository.findByUser(user01);

        Assertions.assertThat(ticketFounds).isNotEmpty();
        Assertions.assertThat(ticketFounds).hasSize(1);
        Assertions.assertThat(ticketFounds.get(0)).isEqualTo(ticket01);
    }
}
