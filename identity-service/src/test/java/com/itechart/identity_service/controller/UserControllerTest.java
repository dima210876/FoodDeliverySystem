package com.itechart.identity_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.identity_service.model.Role;
import com.itechart.identity_service.model.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.sql.Timestamp;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() { }

//    @SneakyThrows
//    @Test
//    void registerValidUser()
//    {
//        User user = User.builder()
//                .email("testCustomer@test.com")
//                .password("1234567890")
//                .firstName("testCustomerName")
//                .lastName("testCustomerSurname")
//                .role(Role.ROLE_CUSTOMER)
//                .expirationDate(new Timestamp(System.currentTimeMillis() + 157680000000L))
//                .build();
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("http://localhost:8081/register")
//                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(user))
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mvc.perform(requestBuilder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").exists());
//    }
//
//    @SneakyThrows
//    @Test
//    void registerNotValidUserImpossible()
//    {
//        User user = User.builder()
//                .email(null)
//                .password("1234567890")
//                .firstName("testCustomerName")
//                .lastName("testCustomerSurname")
//                .role(Role.ROLE_CUSTOMER)
//                .build();
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("http://localhost:8081/register")
//                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(user))
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mvc.perform(requestBuilder)
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").exists());
//    }
//
//    @SneakyThrows
//    @Test
//    public void loginExistingUser() {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("http://localhost:8081/login")
//                .accept(MediaType.APPLICATION_JSON)
//                .content("{\"email\":\"user2@mail.ru\",\"password\":\"1234\"}")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mvc.perform(requestBuilder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.access_token").exists());
//    }
//
//    @SneakyThrows
//    @Test
//    public void loginNonExistingUserImpossible () {
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("http://localhost:8081/login")
//                .accept(MediaType.APPLICATION_JSON)
//                .content("{\"email\":\"nonExisting\",\"password\":\"1234\"}")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mvc.perform(requestBuilder)
//                .andDo(print())
//                .andExpect(status().isUnauthorized())
//                .andExpect(status().reason("Authentication Failed"))
//                .andExpect(unauthenticated());
//    }
}
