package com.example.crudapp.crudapp.Payloads;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev","prod"})
public class ProfileDemo {

    @Value("${message}")
    String message;

    @PostConstruct
    public void printMessage() {

        System.out.println("Message is " + message);
    }
}
