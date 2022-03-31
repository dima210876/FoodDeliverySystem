package com.itechart.identity_service.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.itechart.identity_service.model.User;
import com.itechart.identity_service.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private UserService userService;

    @Test
    void contextLoads() { }

    @Test
    void canLoadExistingUserByUsername() {
        User user = User.builder()
                .email("testUser@test.com")
                .build();

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        UserDetails expected = userService.loadUserByUsername(user.getEmail());

        assertThat(expected).isSameAs(user);

        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    void ShouldThrowExceptionWhenUserDoesNotExist() {
        String notExisting = "test@test.com";

        UsernameNotFoundException thrown = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(notExisting);
        }, "UsernameNotFoundException was expected");

        Assertions.assertEquals(String.format("User %s not found", notExisting), thrown.getMessage());
    }

    @Test
    void saveUser()
    {
        User user = User.builder()
                .email("testUser@test.com")
                .build();

        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        verify(userRepository).findByEmail(user.getEmail());
    }
}