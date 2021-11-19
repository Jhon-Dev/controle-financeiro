package com.jhondev.io.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhondev.io.model.Conta;
import com.jhondev.io.repository.ContaRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/conta")
public class ContaController {

	@Autowired 
	private ContaRepository contaRepository;

	/*Listar todas as contas*/
	@GetMapping(value = "/", produces = "application/json")
	@CacheEvict(value = "cachecontas", allEntries = true)
	@CachePut("cachecontas")
	public ResponseEntity<List<Conta>> conta() throws InterruptedException {

		List<Conta> list = (List<Conta>) contaRepository.findAll();

		return new ResponseEntity<List<Conta>>(list, HttpStatus.OK);

	}
}