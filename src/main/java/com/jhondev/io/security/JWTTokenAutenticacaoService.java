package com.jhondev.io.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jhondev.io.ApplicationContextLoad;
import com.jhondev.io.model.Usuario;
import com.jhondev.io.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService {

	/* Tempo de validade do token 172800000 = 2 dias */
	private static final long EXPIRATION_TIME = 172800000;

	/* Uma senha unica para compor a autenticacao e ajudar na segurança */
	private static final String SECRET = "SenhaExtremamenteSecreta*";

	/* Prefixo padrão de Token */
	private static final String TOKEN_PREFIX = "Bearer";

	/* Prefixo que retorna no cabeçalho a identificação da localização do token */
	private static final String HEADER_STRING = "Authorization";

	/* Gerando token de autenticação e adicionando ao cabeçalho e resposta Http */
	public void addAuthentication(HttpServletResponse response, String username) throws IOException {

		/* Montagem do token */
		String JWT = Jwts.builder() /* Chama o gerador de Token */
				.setSubject(username)/* Adiciona o usuario */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /* Tempo de expiração */
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();/* Compactação e algoritimos de geração de senha */

		/* Junta o token com o prefixo */
		String token = TOKEN_PREFIX + " " + JWT; /* Bearer d9s8d9s8d9s8f9d8f9d89 */

		/* Adiciona no cabeçalho http */
		response.addHeader(HEADER_STRING, token); /* Authorization: Bearer d9s8d9s8d9s8f9d8f9d89 */

		liberacaoCors(response);


		/* Escreve Token como resposta no corpo http */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}

	/*
	 * Retorna o usuário validado com o token ou caso não seja válido retorna null
	 */
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {

		/* Pega o token enviado no cabeçalho http */
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			/* Faz a validação do token do usuário na requisição */
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject(); /* Ex: Usuario João Silva */

			if (user != null) {

				Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
						.findUserByLogin(user);

				if (usuario != null) {

					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(),
							usuario.getAuthorities());

				}
			}
		}
		liberacaoCors(response);
		return null; /* Não autorizado */
	}
	private void liberacaoCors(HttpServletResponse response) {

		if(response.getHeader("Access-Control-Allow-Origin") == null){
			/*Liberando resposta para porta diferente do projeto Angular*/
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		if(response.getHeader("Access-Control-Allow-Headers") == null){
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		if(response.getHeader("Access-Control-Request-Headers") == null){
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		if(response.getHeader("Access-Control-Allow-Methods") == null){
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
}
