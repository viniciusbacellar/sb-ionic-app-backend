package com.vinicius.sbionic.services;

import org.springframework.mail.SimpleMailMessage;

import com.vinicius.sbionic.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
