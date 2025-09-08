package com.project.fileshifter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableAutoConfiguration
public class FileshifterApplication {

	public static void main(String[] args) {


		SpringApplication.run(FileshifterApplication.class, args);

	}

}
