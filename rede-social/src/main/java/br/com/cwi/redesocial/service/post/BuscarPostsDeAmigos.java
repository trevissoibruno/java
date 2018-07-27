package br.com.cwi.redesocial.service.post;

import br.com.cwi.redesocial.dominio.Amizade;
import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IAmizadeRepository;
import br.com.cwi.redesocial.repository.IAvaliacaoRepository;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import br.com.cwi.redesocial.web.dto.ComentarioDto;
import br.com.cwi.redesocial.web.dto.PostDto;
import br.com.cwi.redesocial.web.dto.UsuarioDto;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BuscarPostsDeAmigos {

    @Autowired
    private IPostRepository repository;

    @Autowired
    BuscarUsuarioLogado buscarUsuarioLogado;


    public List<PostDto> buscarPostsAmigos(){

        Usuario usuario = buscarUsuarioLogado.buscarLogado();
        List<Usuario> amigos = new ArrayList<>();
        usuario.getAmizades().stream().forEach(a -> amigos.add(a.getUsuario()));

        List<PostDto> postsSemSenha = new LinkedList<>();

        List<Post> postsBuscados = new LinkedList<>();

        postsBuscados = repository.findAllByUsuario(amigos);

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
