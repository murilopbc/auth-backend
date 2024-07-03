package com.murilo.auth.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;


    // Corrente de filtro de segurança : tudo que minha requisição vai ter que passar
    // (filtros de segurança) que eu estabelecer de forma as requisições realizadas sejam seguras
    // ex: Validações de usuário para saber se ele está apto a realizar uma requisição  ou não

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // STATELESS -> Autenticação via token, não guarda sessão,
                // valida usuário e senha, recebe um token para fazer as requisições dentro da aplicação,
                // se o token for validado ele faz as requisições de acordo com suas permissões
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // O ideal seria que somente os usuários ADM possam criar uma conta
                        .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/inativar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product/ativar/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        // Classe que o SpringSecurity fornece para fazer criptografia de senhas
        return new BCryptPasswordEncoder();
    }


}
