package br.com.cwi.redesocial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AvaliacaoNaoCadastrado extends RuntimeException{

    public AvaliacaoNaoCadastrado(){super("Avaliacao não cadastrada"); }
}
