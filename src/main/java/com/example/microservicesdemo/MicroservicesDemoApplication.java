package com.example.microservicesdemo;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MicroservicesDemoApplication {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesDemoApplication.class, args);
    }

}
