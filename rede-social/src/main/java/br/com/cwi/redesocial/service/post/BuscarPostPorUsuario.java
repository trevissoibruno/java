package br.com.cwi.redesocial.service.post;

import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import br.com.cwi.redesocial.web.dto.ComentarioDto;
import br.com.cwi.redesocial.web.dto.PostDto;
import br.com.cwi.redesocial.web.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class BuscarPostPorUsuario {

    @Autowired
    private IPostRepository repository;

    @Autowired
    private BuscarUsuarioLogado buscarUsuarioLogado;

    public List<PostDto> buscarPorIdUsuario(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            throw new IllegalArgumentException("Pagina Invalida");
        }
        Usuario usuario = buscarUsuarioLogado.buscarLogado();

        List<PostDto> postsSemSenha = new LinkedList<>();

        List<Post> postsBuscados = new LinkedList<>();

        postsBuscados = repository.findByUsuario_Id(usuario.getId(),pageable).getContent();

        for (Post p: postsBuscados) {
            PostDto postDto = new PostDto();
            UsuarioDto usuarioDto = new UsuarioDto();
            postDto.setId(p.getId());
            postDto.setImagem(p.getImagem());
            postDto.setLocalDateTime(formatar(p.getDataHoraPostagem()));
            postDto.setTexto(p.getTexto());
            postDto.setTitulo(p.getTitulo());
            List<ComentarioDto> comentarios = new ArrayList<>();
            for (Comentario c: p.getComentarios()) {
                ComentarioDto comentarioDto = new ComentarioDto();
                comentarioDto.setId(c.getId());
                comentarioDto.setTexto(c.getTexto());
                comentarioDto.setNomeUsuario(c.getUsuario().getNome());
                comentarios.add(comentarioDto);
            }
            postDto.setComentarios(comentarios);
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
