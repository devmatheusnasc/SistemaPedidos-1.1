package com.matheuscruz.sistemapedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscruz.sistemapedidos.domain.Pedido;
import com.matheuscruz.sistemapedidos.repository.PedidoRepository;
import com.matheuscruz.sistemapedidos.services.Exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! ID: " + id + ", TIPO: " + Pedido.class.getName()));
	}
	
}
