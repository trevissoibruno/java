package br.com.cwi.redesocial.web.dto;

import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.dominio.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDto {

    public PostDto(){}

    public Long id;
    public String texto;
    public String localDateTime;
    public String imagem;
    public String titulo;
    public UsuarioDto usuarioDto;
    public List<ComentarioDto> comentarios = new ArrayList<>();
}
