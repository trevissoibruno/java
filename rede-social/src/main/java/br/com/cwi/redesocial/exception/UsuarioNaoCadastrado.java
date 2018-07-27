package br.com.cwi.redesocial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoCadastrado extends RuntimeException{

    public UsuarioNaoCadastrado() {
        super("Usuario não cadastrado");
    }
}
