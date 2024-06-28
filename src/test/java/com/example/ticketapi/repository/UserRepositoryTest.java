package com.example.ticketapi.repository;

import com.example.ticketapi.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByDocumentNumber_thenReturnUser() {
        User user01 = User.builder()
                .documentNumber("12346")
                .firstName("Fabrizio")
                .lastName("Rodriguez")
                .build();
        userRepository.save(user01);
        User userFound = userRepository.findByDocumentNumber("12346");

        Assertions.assertThat(userFound).isNotNull();
        Assertions.assertThat(userFound.getFirstName()).isEqualTo("Fabrizio");
    }
}
