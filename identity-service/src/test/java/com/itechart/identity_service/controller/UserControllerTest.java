package com.itechart.identity_service.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    public void loginExistingUser() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8081/login")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user2@mail.ru\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").exists());
    }

    @SneakyThrows
    @Test
    public void loginNonExistingUserImpossible () {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8081/login")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"nonExisting\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(status().reason("Authentication Failed"))
                .andExpect(unauthenticated());
    }
}
