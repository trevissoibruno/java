package br.com.cwi.redesocial.security;

import br.com.cwi.redesocial.service.usuario.CriptografarSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/*
* Service para iniciar um novo processo de autenticação
* */

@Service
public class AuthenticationService {

    @Value("${security.header.prefix}")
    private String headerPrefix;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private CriptografarSenha criptografarSenha;

    @Autowired
    JwtTokenProvider tokenProvider;

    public String authenticate(String email, String senha) {
        String sha265 = criptografarSenha.cifrar(senha);
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                email,
                sha265
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return headerPrefix + tokenProvider.generateToken(authentication);
    }
}
