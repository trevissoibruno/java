package br.com.cwi.redesocial.exception;

import br.com.cwi.redesocial.dominio.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNaoCadastrado extends RuntimeException{

    public PostNaoCadastrado(){super("Post não cadastrado"); }
}
