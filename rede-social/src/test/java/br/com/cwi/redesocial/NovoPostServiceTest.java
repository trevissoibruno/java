package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.service.post.BuscarPostPorId;
import br.com.cwi.redesocial.service.post.SalvarPostNovo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NovoPostServiceTest {

    @Mock
    private BuscarPostPorId buscarPostPorId;

    @InjectMocks
    private SalvarPostNovo tested;

    @Test(expected = IllegalArgumentException.class)
    public void deveRetornarTextoAusente(){

        Long id = 1L;
        Post post = new Post();
        post.setId(id);

        Mockito.when(buscarPostPorId.buscarPorId(id)).thenReturn(post);

        tested.salvarPost(post);

        Assert.assertEquals(null,post.getTexto());
    }

}
