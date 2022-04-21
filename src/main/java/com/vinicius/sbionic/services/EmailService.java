package com.vinicius.sbionic.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.vinicius.sbionic.domain.Cliente;
import com.vinicius.sbionic.domain.Pedido;

public interface EmailService {

	// Versão de texto plano
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	
	// Versão de email HTML
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
