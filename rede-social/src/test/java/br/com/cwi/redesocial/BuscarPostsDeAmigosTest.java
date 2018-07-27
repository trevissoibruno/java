package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Amizade;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.service.amizade.SalvarNovaAmizade;
import br.com.cwi.redesocial.service.post.BuscarPostPorUsuario;
import br.com.cwi.redesocial.service.post.BuscarPostsDeAmigos;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorId;
import br.com.cwi.redesocial.service.usuario.SalvarUsuarioNovo;
import br.com.cwi.redesocial.web.dto.PostDto;
import javafx.geometry.Pos;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPostsDeAmigosTest {


    @Mock
    private BuscarUsuarioPorId buscarPorId;

    @Mock
    private BuscarPostPorUsuario buscarPostPorUsuario;

    @Mock
    private SalvarUsuarioNovo salvarUsuarioNovo;

    @Mock
    private IPostRepository repository;

    @Mock
    private SalvarNovaAmizade salvarNovaAmizade;

    @Mock
    private BuscarUsuarioLogado buscarUsuarioLogado;


    @InjectMocks
    private BuscarPostsDeAmigos tested;

    @Test
    public void buscarPostsAmigos() {

        Long id1 = 1L;
        Long id2 = 2L;

        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        usuario1.setId(id1);
        usuario2.setId(id2);

        salvarUsuarioNovo.salvarUsuario(usuario1);
        salvarUsuarioNovo.salvarUsuario(usuario2);

        Amizade amizade = new Amizade();
        amizade.setUsuario(usuario2);
        List<Amizade> amizades = new ArrayList<>();
        amizades.add(amizade);

        usuario1.setAmizades(amizades);
        Post post1 = new Post();
        Post post2 = new Post();
        post1.setId(id1);
        post2.setId(id2);

        post1.setUsuario(usuario1);
        post2.setUsuario(usuario2);


        Mockito.when(buscarUsuarioLogado.buscarLogado()).thenReturn(usuario1);
        Mockito.when(buscarPorId.buscarPorId(id2)).thenReturn(usuario2);

        repository.save(post1);
        repository.save(post2);

        salvarNovaAmizade.salvarAmizade(id2);

        List<PostDto> postDtos = tested.buscarPostsAmigos();



        Assert.assertEquals(true,postDtos.contains(post2));

    }


}
