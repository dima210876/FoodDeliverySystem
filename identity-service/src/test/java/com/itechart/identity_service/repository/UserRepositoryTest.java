package com.itechart.identity_service.repository;

import com.itechart.identity_service.model.Role;
import com.itechart.identity_service.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() { }

    @AfterEach
    void clearRepository() { userRepository.deleteAll(); }

    @Test
    void canFindSavedUserByEmail()
    {
        String email = "testCustomer@test.com";
        User user = User.builder()
                .email(email)
                .password("1234567890")
                .firstName("testCustomerName")
                .lastName("testCustomerSurname")
                .role(Role.ROLE_CUSTOMER)
                .build();

        userRepository.save(user);

        Optional<User> optionalUserFoundByEmail = userRepository.findByEmail(email);
        assertThat(optionalUserFoundByEmail).isPresent();
        User userFoundByEmail = optionalUserFoundByEmail.get();
        assertThat(userFoundByEmail.getEmail()).isEqualTo(email);
    }

    @Test
    void returnEmptyOptionalUserWhenTryingToFindUserByNotRegisteredEmail()
    {
        String email = "testCustomer@test.com";
        Optional<User> optionalUserFoundByEmail = userRepository.findByEmail(email);
        assertThat(optionalUserFoundByEmail).isEmpty();
    }
}