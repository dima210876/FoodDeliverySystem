package com.itechart.identity_service.repository;

import com.itechart.identity_service.model.Role;
import com.itechart.identity_service.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void itShouldFindUserByEmail() {
        String testEmail = "testUser@test.com";

        User user = User.builder()
                .email(testEmail)
                .password("testPass")
                .firstName("testFirstName")
                .lastName("testLastName")
                .role(Role.MANAGER)
                .build();
        userRepository.save(user);

        Optional<User> resultUser = userRepository.findByEmail(testEmail);

        assertThat(resultUser.get()).isEqualTo(user);
    }

    @Test
    void itShouldNotFindUserThatDoesNotExistByEmail() {
        String testEmail = "testUser@test.com";

        Optional<User> resultUser = userRepository.findByEmail(testEmail);

        assertThat(resultUser.isEmpty()).isTrue();
    }
}