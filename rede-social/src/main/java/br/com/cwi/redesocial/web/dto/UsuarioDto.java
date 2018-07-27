package br.com.cwi.redesocial.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class UsuarioDto {

    public UsuarioDto(){}

    public String nome;
    public String imagem;
    public String email;

}
