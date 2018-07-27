package br.com.cwi.redesocial.service.post;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SalvarPostNovo {

    @Autowired
    private IPostRepository repository;

    @Autowired
    private BuscarUsuarioLogado buscarUsuarioLogado;

    public void salvarPost(Post post){

        if(Objects.isNull(post.getTexto()) || post.getTexto().isEmpty()){
            throw new IllegalArgumentException("Texto do post deve ser informado");
        }
        Usuario usuario = buscarUsuarioLogado.buscarLogado();

        post.setDataHoraPostagem(LocalDateTime.now());
        post.setUsuario(usuario);
        repository.save(post);
    }
}
