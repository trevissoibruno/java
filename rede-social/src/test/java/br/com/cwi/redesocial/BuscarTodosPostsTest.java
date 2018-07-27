package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IPostRepository;
import br.com.cwi.redesocial.service.post.BuscarPosts;
import br.com.cwi.redesocial.web.dto.PostDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
public class BuscarTodosPostsTest {

    @InjectMocks
    private BuscarPosts tested;

    @Mock
    private IPostRepository repository;


    @Test
    public void BuscarPosts(){

        Long id = 1L;
        Post post = new Post();
        post.setId(id);

        Usuario usuario = new Usuario();
        usuario.setId(id);

        Comentario comentario = new Comentario();
        comentario.setId(id);
        List<Comentario> comentarios = new ArrayList<>();
        comentarios.add(comentario);

        post.setUsuario(usuario);
        post.setComentarios(comentarios);

        List<Post> posts = new ArrayList<>();
        posts.add(post);


        Mockito.when(repository.findById(id)).thenReturn(Optional.of(post));

        Assert.assertEquals(true,posts.contains(post));

    }


}
