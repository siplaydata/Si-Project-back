package com.example.cocktail;

import com.example.cocktail.Configuration.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfig.class)
public class CocktailApplication {
	public static void main(String[] args) {
		SpringApplication.run(CocktailApplication.class, args);
	}
}
