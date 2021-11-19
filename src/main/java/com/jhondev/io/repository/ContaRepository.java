package com.jhondev.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jhondev.io.model.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long>{

}
