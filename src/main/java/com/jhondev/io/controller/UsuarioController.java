package com.jhondev.io.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhondev.io.model.Usuario;
import com.jhondev.io.repository.UsuarioRepository;

@CrossOrigin
@RestController /* Arquitetura REST */
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired /* Se fosse CDI seria @ Inject */
	private UsuarioRepository usuarioRepository;

	/* Serviço RESTful */
	@GetMapping(value = "/{id}/codigovenda/{venda}", produces = "application/json")
	public ResponseEntity<Usuario> relatorio(@PathVariable(value = "id") Long id 
			                                ,@PathVariable(value = "venda") Long venda) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	/* LISTAR todos os usuários */
	@GetMapping(value = "/", produces = "application/json")
	@CacheEvict(value = "cacheusuarios", allEntries = true)
	@CachePut("cacheusuarios")
	public ResponseEntity<List<Usuario>> usuario() throws InterruptedException {

		List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	/* LISTAR usuário pelo ID */
	@GetMapping(value = "{id}", produces = "application/json", headers = "X-API-Version=v1")
	public ResponseEntity<Usuario> initV1(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);
        System.out.println("Execultando versão 1");
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}
	
	/* LISTAR usuário pelo ID */
	@GetMapping(value = "{id}", produces = "application/json", headers = "X-API-Version=v2")
	@Cacheable("cacheuser")
	public ResponseEntity<Usuario> initV2(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);
        System.out.println("Execultando versão 2");
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	

	/* DELETAR usuário pelo ID */
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable(value = "id") Long id) {

		usuarioRepository.deleteById(id);

		return "ok";
	}

	/* CADASTRAR um usuário */
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

		String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhacriptografada);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

	}

	/* ATUALIZAR um usuário */
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		
		Usuario userTemporario = usuarioRepository.findUserByLogin(usuario.getLogin());

		if (!userTemporario.getSenha().equals(usuario.getSenha())) {  /*Senhas diferentes*/
			String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(senhacriptografada);
			
		}
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

	}
}
