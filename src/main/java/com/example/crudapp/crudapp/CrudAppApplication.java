package com.example.crudapp.crudapp;

import com.example.crudapp.crudapp.config.YAMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CrudAppApplication implements CommandLineRunner {

	@Autowired
	private YAMLConfig myConfig;

	@Value("${name}")
	private String injectedValue;

	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("using environment: " + myConfig.getEnvironment());
		System.out.println("name: " + myConfig.getName());
		System.out.println("enabled: " + myConfig.isEnabled());
		System.out.println("servers: " + myConfig.getServers());
		System.out.println("Name: " + injectedValue);
		System.out.println("ENV: " + env.getProperty("environment"));
	}
}
