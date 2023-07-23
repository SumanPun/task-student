package com.example.crudapp.crudapp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTask {


    @Autowired
    private BirthdayService birthdayService;

    @Scheduled(cron = "0 38 17 * * ?") // Run at midnight every day
    public void sendBirthdayMessages() {
        birthdayService.sendBirthdayMessage();
    }
}
