package com.example.crudapp.crudapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduledTaskConfig {

    @Bean(name = "myScheduledTask")
    public ScheduledTask scheduledTask() {
        return new ScheduledTask();
    }
}
