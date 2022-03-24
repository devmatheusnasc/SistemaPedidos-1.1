package com.matheuscruz.sistemapedidos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscruz.sistemapedidos.domain.Cliente;
import com.matheuscruz.sistemapedidos.repository.ClienteRepository;
import com.matheuscruz.sistemapedidos.services.Exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! ID: " + id + ", TIPO: " + Cliente.class.getName()));
	}

}
