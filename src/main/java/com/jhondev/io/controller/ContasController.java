package com.jhondev.io.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhondev.io.model.Contas;
import com.jhondev.io.repository.ContasRepository;

@RestController 
@RequestMapping(value = "/contas")
public class ContasController {

	@Autowired /* Se fosse CDI seria @ Inject */
	private ContasRepository contasRepository;

	/* Listar por ID */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Contas> init(@PathVariable(value = "id") Long id) {
		Optional<Contas> contas = contasRepository.findById(id);

		return new ResponseEntity<Contas>(contas.get(), HttpStatus.OK);

	}
	

	/* Listar todas as contas */
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Contas>> conta() {
		List<Contas> list = (List<Contas>) contasRepository.findAll();

		return new ResponseEntity<List<Contas>>(list, HttpStatus.OK);

	}

}
