package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.exception.UsuarioNaoCadastrado;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorEmail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioPorCpfServiceTest {

    @InjectMocks
    private BuscarUsuarioPorEmail tested;

    @Mock
    private IUsuarioRepository repository;

    @Test
    public void deveRetornarClienteExistente() {

        // Cenário
        String email = "teste@teste.com.br";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        // Cenário - Mocks
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // Método que deve ser testado
        Usuario usuario1Retornado = tested.buscarPorEmail(email);

        // Verificações
        Assert.assertNotNull(usuario1Retornado);
        Assert.assertEquals(email, usuario1Retornado.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExceptionQuandoIdNulo() {
        tested.buscarPorEmail(null);
    }

    @Test(expected = UsuarioNaoCadastrado.class)
    public void deveLancarExceptionQuandoUsuarioInexistente() {

        // Cenário
        String email = "teste@teste.com.br";

        // Cenário - mocks
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.empty());

        // Método que deve ser testado
        Usuario usuarioRetornado = tested.buscarPorEmail(email);

    }

}
