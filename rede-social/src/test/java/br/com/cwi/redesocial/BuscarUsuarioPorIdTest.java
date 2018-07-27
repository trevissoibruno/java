package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.exception.UsuarioNaoCadastrado;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioPorIdTest {

    @InjectMocks
    private BuscarUsuarioPorId tested;

    @Mock
    private IUsuarioRepository repository;

    @Test
    public void deveRetornarUsuarioExistente(){

        // Cenário
        Long id = 1L;
        Usuario usuario =new Usuario();
        usuario.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario usuarioRetornado = tested.buscarPorId(id);

        Assert.assertNotNull(usuarioRetornado);
        Assert.assertEquals(id,usuarioRetornado.getId());

    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExceptionQuandoIdNulo(){

        tested.buscarPorId(null);
    }

    @Test(expected = UsuarioNaoCadastrado.class)
    public void deveLancarExceptionQuandoUsuarioInesistente (){
        Long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Usuario usuarioRetornado = tested.buscarPorId(id);
    }


}
