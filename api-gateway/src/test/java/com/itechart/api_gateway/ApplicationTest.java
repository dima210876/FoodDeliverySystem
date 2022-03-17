package com.itechart.api_gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWireMock(port = 0)
public class ApplicationTest
{
    @Autowired
    private WebTestClient webClient;

    @Test
    public void getSecuredResourceWithoutAccessToken() throws Exception
    {
        stubFor(get(urlEqualTo("http://localhost:8083/"))
                .willReturn(aResponse()
                        .withStatus(200)));

        webClient
                .get().uri("/restaurantInfo")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody();
    }

    /*
    @Test
    public void getGeneralAccessResource()
    {
        stubFor(get(urlEqualTo("http://localhost:8080/menu"))
                .willReturn(aResponse()
                        .withBody("{\"items\": [ {\"name\": \"item1\"} ] }")
                        .withHeader("Content-Type", "application/json")));

        webClient
                .get().uri("/foodDelivery/menu")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.items").isArray();
    }
    */
}
