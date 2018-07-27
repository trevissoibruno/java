package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.exception.PostNaoCadastrado;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.service.post.BuscarPostPorId;
import javafx.geometry.Pos;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPostPorIdTest {


    @InjectMocks
    private BuscarPostPorId tested;

    @Mock
    IPostRepository repository;


    @Test
    public void deveRetornarPostExistente(){

        Long id = 1L;
        Post post = new Post();
        post.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(post));

        Post postRetornado = tested.buscarPorId(id);

        Assert.assertNotNull(postRetornado);
        Assert.assertEquals(id,postRetornado.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExceptionQuandoIdNulo() {
        tested.buscarPorId(null);
    }


    @Test(expected = PostNaoCadastrado.class)
    public void deveLancarExceptionQuandoClienteInexistente() {

        // Cenário
        Long id = 1L;

        // Cenário - mocks
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        // Método que deve ser testado
        Post postRetornado = tested.buscarPorId(id);

    }

}
