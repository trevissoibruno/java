package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Avaliacao;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IAvaliacaoRepository;
import br.com.cwi.redesocial.service.avaliacao.DeletarAvaliacao;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DeletarAvaliacaoTest {


    @InjectMocks
    private DeletarAvaliacao tested;

    @Mock
    private IAvaliacaoRepository repository;

    @Mock
    private BuscarUsuarioLogado buscarUsuarioLogado;

    @Test
    public void naoDeveRetornarAvaliacao(){

        Long id = 1L;
        Post post = new Post();
        post.setId(id);

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);

        List<Avaliacao> avaliacoes = new ArrayList<>();
        avaliacoes.add(avaliacao);

        post.setAvaliacoes(avaliacoes);

        Usuario usuario = new Usuario();
        usuario.setId(id);

        avaliacao.setUsuario(usuario);

        tested.deletarAvaliacao(id);

        Mockito.when(buscarUsuarioLogado.buscarLogado()).thenReturn(usuario);

        Mockito.verify(repository, Mockito.times(1)).findByPost_IdAndUsuario_Id(post.getId(),usuario.getId());

        Mockito.verify(repository, Mockito.times(1)).delete(avaliacao);

    }
}
