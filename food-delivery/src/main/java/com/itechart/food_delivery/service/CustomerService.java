package com.itechart.food_delivery.service;

import com.itechart.food_delivery.config.DeletingUserConfig;
import com.itechart.food_delivery.dto.CustomerDTO;
import com.itechart.food_delivery.dto.IdentityRegistrationDTO;
import com.itechart.food_delivery.exception.CustomerRegistrationException;
import com.itechart.food_delivery.exception.GettingInfoException;
import com.itechart.food_delivery.model.Customer;
import com.itechart.food_delivery.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RabbitTemplate rabbitTemplate;

    @LoadBalanced
    private final RestTemplate restTemplate;

    @Transactional
    public Customer registerCustomer(@Valid CustomerDTO customerDTO) throws CustomerRegistrationException {
        Customer customer;
        Long userId = 0L;

        try {
            final String IDENTITY_REGISTER_URL = "http://IDENTITY-SERVICE/register";
            final String ROLE_CUSTOMER = "ROLE_CUSTOMER";

            IdentityRegistrationDTO identityRegistrationDTO = IdentityRegistrationDTO.builder()
                    .email(customerDTO.getEmail())
                    .password(customerDTO.getPassword())
                    .lastName(customerDTO.getLastName())
                    .firstName(customerDTO.getFirstName())
                    .role(ROLE_CUSTOMER)
                    .build();

            ResponseEntity<IdentityRegistrationDTO> response = restTemplate
                    .postForEntity(IDENTITY_REGISTER_URL, identityRegistrationDTO, IdentityRegistrationDTO.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new CustomerRegistrationException("Identity service couldn't register the manager");
            }

            userId = response.getBody().getId();

            customer = Customer.builder()
                    .userId(userId)
                    .email(customerDTO.getEmail())
                    .firstName(customerDTO.getFirstName())
                    .lastName(customerDTO.getLastName())
                    .phoneNumber(customerDTO.getPhoneNumber())
                    .role(ROLE_CUSTOMER)
                    .build();

            customer = customerRepository.save(customer);
        } catch (Throwable ex) {
            rabbitTemplate.convertAndSend(
                    DeletingUserConfig.EXCHANGE,
                    DeletingUserConfig.ROUTING_KEY,
                    userId
            );
            throw new CustomerRegistrationException(ex.getMessage());
        }

        return customer;
    }

    public Customer getCustomerInfo(Long customerId) throws GettingInfoException {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findByUserId(customerId);

            if (optionalCustomer.isEmpty()) {
                throw new GettingInfoException(String.format("Customer with id %d doesn't exist", customerId));
            }
            return optionalCustomer.get();
        } catch (Throwable ex) {
            throw new GettingInfoException("Couldn't get customer with id " + customerId);
        }
    }
}
