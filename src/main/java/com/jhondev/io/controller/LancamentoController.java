package com.jhondev.io.controller;

import com.jhondev.io.model.Lancamento;
import com.jhondev.io.model.Usuario;
import com.jhondev.io.repository.LancamentoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "API REST Lançamentos")
@CrossOrigin
@RestController
@RequestMapping(value = "/api/finance")
public class LancamentoController {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@GetMapping(value = "/lancamentos", produces = "application/json")
	public ResponseEntity<List<Lancamento>> listaLancamentos() throws InterruptedException {

		List<Lancamento> list = (List<Lancamento>) lancamentoRepository.findAll();

		return new ResponseEntity<List<Lancamento>>(list, HttpStatus.OK);

	}

	@PostMapping(value = "/lancamento/cadastrar", produces = "application/json")
	public ResponseEntity<Lancamento> cadastrar(@RequestBody Lancamento lancamento) {

		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

		return new ResponseEntity<Lancamento>(lancamentoSalvo, HttpStatus.OK);
	}
	
	/*DELETAR*/
	@DeleteMapping(value = "/lancamento/{id}", produces = "application/text")
	public String delete(@PathVariable(value = "id") Long id) {

		lancamentoRepository.deleteById(id);
		
		return "Lançamento deletado";
	}

	@PutMapping(value = "lancamento/atualizar", produces = "application/json")
	public ResponseEntity<Lancamento> atualizarLancamento(@RequestBody Lancamento lancamento) {

		Lancamento lancamentoAtualizado = lancamentoRepository.save(lancamento);

		return new ResponseEntity<Lancamento>(lancamentoAtualizado, HttpStatus.OK);

	}
}
