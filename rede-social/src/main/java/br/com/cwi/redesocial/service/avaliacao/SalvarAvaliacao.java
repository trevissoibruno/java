package br.com.cwi.redesocial.service.avaliacao;

import br.com.cwi.redesocial.dominio.Avaliacao;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IAvaliacaoRepository;
import br.com.cwi.redesocial.service.post.BuscarPostPorId;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SalvarAvaliacao {

    @Autowired
    private IAvaliacaoRepository repository;

    @Autowired
    private BuscarUsuarioLogado buscarUsuarioLogado;

    @Autowired
    private BuscarPostPorId buscarPostPorId;


    public void salvarAvaliacao(Long idPost){

        if (Objects.isNull(idPost)) {
            throw new IllegalArgumentException("Id do Post Invalido");
        }
        Avaliacao avaliacao = new Avaliacao();
        Usuario usuario = buscarUsuarioLogado.buscarLogado();
        Post post = buscarPostPorId.buscarPorId(idPost);
        avaliacao.setPost(post);
        avaliacao.setUsuario(usuario);
        repository.save(avaliacao);
    }
}
