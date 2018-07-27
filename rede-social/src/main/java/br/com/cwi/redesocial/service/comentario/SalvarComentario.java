package br.com.cwi.redesocial.service.comentario;

import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IComentarioRepository;
import br.com.cwi.redesocial.service.post.BuscarPostPorId;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SalvarComentario {

    @Autowired
    private IComentarioRepository repository;

    @Autowired
    private BuscarPostPorId buscarPostPorId;

    @Autowired
    private BuscarUsuarioLogado buscarUsuarioLogado;

    public void salvarComentario(Comentario comentario,Long idPost){

        if (Objects.isNull(idPost)) {
            throw new IllegalArgumentException("Id do Post Invalido");
        }

        if (Objects.isNull(comentario)) {
            throw new IllegalArgumentException("Comentario invalido");
        }

        Usuario usuario = buscarUsuarioLogado.buscarLogado();
        Post post = buscarPostPorId.buscarPorId(idPost);
        comentario.setPost(post);
        comentario.setUsuario(usuario);
        repository.save(comentario);

    }
}
