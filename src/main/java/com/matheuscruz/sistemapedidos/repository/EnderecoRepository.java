package com.matheuscruz.sistemapedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuscruz.sistemapedidos.domain.Endereco;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
