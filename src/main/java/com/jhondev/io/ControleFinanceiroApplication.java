package com.jhondev.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EntityScan(basePackages = {"com.jhondev.io.model"})
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories (basePackages = {"com.jhondev.io.repository"})
@EnableTransactionManagement
@RestController
@EnableCaching
public class ControleFinanceiroApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ControleFinanceiroApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123")); //Para Criptografar uma senha!
	}

	/*Mapeamento global que reflete em todo o sistema*/
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
		/*Liberando o mapeamento de usuário para todas as origens*/
	}
}
