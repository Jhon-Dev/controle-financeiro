package com.jhondev.io.repository;

import com.jhondev.io.model.Lancamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LancamentoRepository extends CrudRepository<Lancamento, Long>{

}
