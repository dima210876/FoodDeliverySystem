package com.itechart.courier_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:3000"})
@SpringBootApplication
@EnableEurekaClient
public class CourierManagerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CourierManagerApplication.class, args);
    }
}
