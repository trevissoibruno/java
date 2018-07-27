package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IComentarioRepository;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.service.comentario.SalvarComentario;
import br.com.cwi.redesocial.service.post.BuscarPostPorId;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class SaveComentarioTest {

    @Mock
    private IPostRepository postRepository;

    @Mock
    private BuscarPostPorId buscarPostPorId;

    @Mock
    private IComentarioRepository repository;

    @Mock
    private BuscarUsuarioLogado buscarUsuarioLogado;

    @Captor
    ArgumentCaptor<Comentario> captadorDeComentario;

    @InjectMocks
    private SalvarComentario tested;

    @Test(expected = IllegalArgumentException.class)
    public void deveReceberIdNuloENaoFazerNadaId() {
        Comentario comentario = new Comentario();
        tested.salvarComentario(comentario,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveReceberIdNuloENaoFazerNadaComentario() {
        Long id = 1L;
        tested.salvarComentario(null,id);
    }

    @Test
    public void deveIncluirComentario() {

        // Cenário
        Long id = 1L;

        Usuario usuario = new Usuario();
        usuario.setId(id);
        Mockito.when(buscarUsuarioLogado.buscarLogado()).thenReturn(usuario);

        Post post = new Post();
        post.setId(id);


        Comentario comentario = new Comentario();
        comentario.setUsuario(usuario);

        // Execução
        tested.salvarComentario(comentario,post.getId());

        // Validações
        Mockito.verify(buscarUsuarioLogado, Mockito.times(1)).buscarLogado();
        Mockito.verify(repository, Mockito.times(1)).save(captadorDeComentario.capture());

        Comentario comentarioCapturado = captadorDeComentario.getValue();

        Assert.assertTrue(usuario == comentarioCapturado.getUsuario());

    }


}
