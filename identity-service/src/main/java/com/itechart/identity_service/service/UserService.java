package com.itechart.identity_service.service;

import com.itechart.identity_service.config.RegistrationMessagingConfig;
import com.itechart.identity_service.exception.EmailDuplicationException;
import com.itechart.identity_service.model.User;
import com.itechart.identity_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class UserService implements UserDetailsService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) { return user.get(); }
        else {
            throw new UsernameNotFoundException(String.format("User %s not found", email));
        }
    }

    public User saveUser(User user) throws EmailDuplicationException
    {
        if (!isEmailUnique(user.getEmail()))
        {
            throw new EmailDuplicationException("Email is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setExpirationDate(new Timestamp(System.currentTimeMillis() + 157680000000L));
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);
        rabbitTemplate.convertAndSend(
                RegistrationMessagingConfig.EXCHANGE,
                RegistrationMessagingConfig.ROUTING_KEY,
                savedUser.getEmail()
        );
        return savedUser;
    }

    public boolean isEmailUnique(String email)
    {
        return userRepository.findByEmail(email).isEmpty();
    }
}
