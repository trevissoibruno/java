package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.service.post.BuscarPostPorUsuario;
import br.com.cwi.redesocial.service.post.SalvarPostNovo;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorId;
import javafx.geometry.Pos;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPostPorIdUsuarioTest {

    @Mock
    private IPostRepository repository;

    @Mock
    private BuscarUsuarioLogado buscarUsuarioLogado;

    @InjectMocks
    private SalvarPostNovo tested;

    @Test
    public void buscarPostPorUsuario(){

        Post post = new Post();
        post.setTexto("texto");
        Usuario usuario = new Usuario();

        Mockito.when(buscarUsuarioLogado.buscarLogado()).thenReturn(usuario);

        tested.salvarPost(post);

        Mockito.verify(repository, Mockito.times(1)).save(post);

        Assert.assertNotNull(post.getUsuario());
        Assert.assertEquals(usuario, post.getUsuario());
        Assert.assertNotNull(post.getDataHoraPostagem());
    }

}
