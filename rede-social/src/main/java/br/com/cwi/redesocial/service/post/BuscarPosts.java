package br.com.cwi.redesocial.service.post;

import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.web.dto.PostDto;
import br.com.cwi.redesocial.web.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class BuscarPosts {

    @Autowired
    private IPostRepository repository;

    public List<PostDto> buscar(int pagina) {
        List<PostDto> postsSemSenha = new LinkedList<>();

        List<Post> postsBuscados = new LinkedList<>();

        postsBuscados = repository.findAll(new PageRequest(pagina,5)).getContent();

        for (Post p: postsBuscados) {
            PostDto postDto = new PostDto();
            UsuarioDto usuarioDto = new UsuarioDto();
            postDto.setImagem(p.getImagem());
            postDto.setLocalDateTime(formatar(p.getDataHoraPostagem()));
            postDto.setTexto(p.getTexto());
            usuarioDto.setEmail(p.getUsuario().getEmail());
            usuarioDto.setNome(p.getUsuario().getNome());
            usuarioDto.setImagem(p.getUsuario().getImagem());
            postDto.setUsuarioDto(usuarioDto);
            postsSemSenha.add(postDto);
        }
            return postsSemSenha;
    }

    public String formatar(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }
}
