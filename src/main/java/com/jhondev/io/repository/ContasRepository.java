package com.jhondev.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jhondev.io.model.Contas;

@Repository
public interface ContasRepository extends CrudRepository<Contas, Long>{

}
