package com.jhondev.io.controller;

import com.jhondev.io.model.Lancamento;
import com.jhondev.io.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/finance")
public class LancamentoController {

	@Autowired
	private LancamentoRepository contaRepository;

	@GetMapping(value = "/lancamentos", produces = "application/json")
	public ResponseEntity<List<Lancamento>> listaLancamentos() throws InterruptedException {

		List<Lancamento> list = (List<Lancamento>) contaRepository.findAll();

		return new ResponseEntity<List<Lancamento>>(list, HttpStatus.OK);

	}

	@PostMapping(value = "/lancamento/cadastrar", produces = "application/json")
	public ResponseEntity<Lancamento> cadastrar(@RequestBody Lancamento lancamento) {

		Lancamento lancamentoSalvo = contaRepository.save(lancamento);

		return new ResponseEntity<Lancamento>(lancamentoSalvo, HttpStatus.OK);
	}
	
	/*DELETAR*/
	@DeleteMapping(value = "/lancamento/{id}", produces = "application/text")
	public String delete(@PathVariable(value = "id") Long id) {
		
		contaRepository.deleteById(id);
		
		return "Lançamento deletado";
	}
}
