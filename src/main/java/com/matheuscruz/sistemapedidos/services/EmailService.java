package com.matheuscruz.sistemapedidos.services;

import org.springframework.mail.SimpleMailMessage;

import com.matheuscruz.sistemapedidos.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
