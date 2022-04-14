package com.vinicius.sbionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vinicius.sbionic.services.DBService;
import com.vinicius.sbionic.services.EmailService;
import com.vinicius.sbionic.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	// Pega o valor da chave em application-dev.properties e armazena na variavel strategy
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		// Verificação da chave de criação do banco de dados
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}
	
	// Instanciação do EmailService com o SmtpEmailService para configuração Dev
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
