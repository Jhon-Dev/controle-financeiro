package com.jhondev.io.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jhondev.io.service.ImplementacaoUserDetailService;

/*Mapeia URL endereços, autoriza e bloqueia acessos a URL */
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailService implementacaoUserDetailService;

	/* Configura as solicitações de acessos por Http */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* Ativando a proteção contra usuários que não estão validados por token */
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		
		/* Ativando a permissão para acesso a pagina inicial do sistema (ex: sistema.com.br/) */
		.disable().authorizeRequests()
		.antMatchers("/**").permitAll()
		.antMatchers("/api/**").permitAll()

		/* Ativando a permissão para acesso a pagina inicial especifica do sistema (ex: sistema.com.br/index) */
		.antMatchers("/index").permitAll()
		.antMatchers("/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs","/webjars/**").permitAll()

		/* Url de Logout - Redireciona após o user deslogar do sistema */
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		
		/* Mapeia Url de logout e invalida  o usuário */
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		/* Filtra requisições de login para autenticação */
		.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		
		/* Filtra demais requisições para verificar a presença do Token JWT no header http */
		.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/* Service que ira consultar o usuário no banco de dados */
		auth.userDetailsService(implementacaoUserDetailService)

				/* Padrão de codificação */
				.passwordEncoder(new BCryptPasswordEncoder());
	}

}
