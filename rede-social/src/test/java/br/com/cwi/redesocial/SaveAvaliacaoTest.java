package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Avaliacao;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IAvaliacaoRepository;
import br.com.cwi.redesocial.service.avaliacao.BuscarAvaliacaoPorIdPost;
import br.com.cwi.redesocial.service.avaliacao.SalvarAvaliacao;
import br.com.cwi.redesocial.service.post.BuscarPostPorId;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SaveAvaliacaoTest {

    @Mock
    private IAvaliacaoRepository repository;

    @Mock
    private BuscarUsuarioLogado buscarUsuarioLogado;
    @Mock
    private BuscarAvaliacaoPorIdPost buscarAvaliacaoPorIdPost;

    @Mock
    private BuscarPostPorId buscarPostPorId;



    @Captor
    ArgumentCaptor<Avaliacao> captadorDeAvaliacao;

    @InjectMocks
    private SalvarAvaliacao tested;

    @Test(expected = IllegalArgumentException.class)
    public void deveReceberIdNuloENaoFazerNada() {
        tested.salvarAvaliacao(null);
    }

    @Test
    public void deveIncluirAvaliacao() {

        // Cenário
        Long id = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(id);
        Post post = new Post();
        post.setId(id);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);
        List<Avaliacao> avaliacaos = new ArrayList<>();
        Mockito.when(buscarUsuarioLogado.buscarLogado()).thenReturn(usuario);
        Mockito.when(buscarPostPorId.buscarPorId(id)).thenReturn(post);
        Mockito.when(buscarAvaliacaoPorIdPost.buscarAvaliacao(id)).thenReturn(avaliacaos);


        // Execução
        tested.salvarAvaliacao(id);

        // Validações
        Mockito.verify(buscarUsuarioLogado, Mockito.times(1)).buscarLogado();

        Mockito.verify(repository, Mockito.times(1)).save(captadorDeAvaliacao.capture());

        Avaliacao avaliacaoCapturado = captadorDeAvaliacao.getValue();

        Assert.assertTrue(usuario == avaliacaoCapturado.getUsuario());
        Assert.assertEquals(post,avaliacaoCapturado.getPost());
    }
}
