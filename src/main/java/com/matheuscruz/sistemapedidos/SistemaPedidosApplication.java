package com.matheuscruz.sistemapedidos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheuscruz.sistemapedidos.domain.Categoria;
import com.matheuscruz.sistemapedidos.repository.CategoriaRepository;

@SpringBootApplication
public class SistemaPedidosApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemaPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		
	}

}
