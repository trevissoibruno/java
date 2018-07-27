package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Avaliacao;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.exception.AvaliacaoNaoCadastrado;
import br.com.cwi.redesocial.repository.IAvaliacaoRepository;
import br.com.cwi.redesocial.service.avaliacao.BuscarAvaliacaoPorIdPost;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
public class BuscarAvaliacaoPorIdPostTest {

    @InjectMocks
    private BuscarAvaliacaoPorIdPost tested;

    @Mock
    private IAvaliacaoRepository repository;

    @Test
    public void deveRetornarAvaliacoesExistente() {

        // Cenário
        Long id = 1L;
        Post post = new Post();
        post.setId(id);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(id);


        List<Avaliacao> avaliacoes = new ArrayList<>();
        avaliacoes.add(avaliacao);
        post.setAvaliacoes(avaliacoes);

        // Cenário - Mocks
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(avaliacao));

        // Método que deve ser testado
        avaliacoes = tested.buscarAvaliacao(id);

        // Verificações
        Assert.assertNotNull(avaliacoes);

    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExceptionQuandoIdNulo() {
        tested.buscarAvaliacao(null);
    }

    @Test(expected = AvaliacaoNaoCadastrado.class)
    public void deveLancarExceptionQuandoAvaliacaoInexistente() {

        // Cenário
        Long id = 1L;

        // Cenário - mocks
        Mockito.when(repository.findById(id)).thenReturn(null);

        List<Avaliacao> avaliacoes = new ArrayList<>();

        // Método que deve ser testado
        avaliacoes = tested.buscarAvaliacao(id);

    }
}
