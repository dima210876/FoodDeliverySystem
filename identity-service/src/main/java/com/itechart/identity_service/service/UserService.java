package com.itechart.identity_service.service;

import com.itechart.identity_service.config.RegistrationConfirmationMessagingConfig;
import com.itechart.identity_service.config.RegistrationMessagingConfig;
import com.itechart.identity_service.dto.ConfirmationInfoDto;
import com.itechart.identity_service.exception.EmailDuplicationException;
import com.itechart.identity_service.exception.RegistrationConfirmationTokenException;
import com.itechart.identity_service.model.RegistrationConfirmationToken;
import com.itechart.identity_service.model.User;
import com.itechart.identity_service.repository.RegistrationConfirmationTokenRepository;
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
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class UserService implements UserDetailsService
{
    private final UserRepository userRepository;
    private final RegistrationConfirmationTokenRepository registrationConfirmationTokenRepository;
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
        user.setEnabled(false);
        User savedUser = userRepository.saveAndFlush(user);
        RegistrationConfirmationToken savedToken = registrationConfirmationTokenRepository.saveAndFlush(
                RegistrationConfirmationToken.builder()
                        .user(savedUser)
                        .confirmationToken(UUID.randomUUID().toString().replace("-", ""))
                        .isActive(true)
                        .build()
        );
        ConfirmationInfoDto confirmationInfoDto = ConfirmationInfoDto.builder()
                .email(user.getEmail())
                .confirmationToken(savedToken.getConfirmationToken())
                .build();

        rabbitTemplate.convertAndSend(
                RegistrationConfirmationMessagingConfig.EXCHANGE,
                RegistrationConfirmationMessagingConfig.ROUTING_KEY,
                confirmationInfoDto
        );
        return savedUser;
    }

    public String confirmUserRegistration(String confirmationToken) throws RegistrationConfirmationTokenException
    {
        Optional<RegistrationConfirmationToken> token =
                registrationConfirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token.isEmpty())
        {
            throw new RegistrationConfirmationTokenException("Registration confirmation token not found");
        }
        RegistrationConfirmationToken registrationConfirmationToken = token.get();
        if (!registrationConfirmationToken.isActive())
        {
            throw new RegistrationConfirmationTokenException("Registration confirmation token not active");
        }
        Optional<User> optionalUser = userRepository.findById(registrationConfirmationToken.getUser().getId());
        if (optionalUser.isEmpty())
        {
            throw new RegistrationConfirmationTokenException("User for registration confirmation token not found");
        }
        User user = optionalUser.get();
        user.setEnabled(true);
        User confirmedUser = userRepository.save(user);
        registrationConfirmationToken.setActive(false);
        registrationConfirmationTokenRepository.save(registrationConfirmationToken);
        rabbitTemplate.convertAndSend(
                RegistrationMessagingConfig.EXCHANGE,
                RegistrationMessagingConfig.ROUTING_KEY,
                confirmedUser.getEmail()
        );
        return "Your account has been activated.";
    }

    public boolean isEmailUnique(String email)
    {
        return userRepository.findByEmail(email).isEmpty();
    }
}
