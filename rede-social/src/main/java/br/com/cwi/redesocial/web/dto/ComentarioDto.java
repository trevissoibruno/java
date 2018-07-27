package br.com.cwi.redesocial.web.dto;

import br.com.cwi.redesocial.dominio.Comentario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDto {

    public ComentarioDto(){};

    public Long id;
    public String texto;
    public String nomeUsuario;


}
