package br.com.fermentis.tccrecipeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class TccRecipeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccRecipeServiceApplication.class, args);
	}

}
