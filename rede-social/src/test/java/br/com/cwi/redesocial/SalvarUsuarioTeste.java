package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorEmail;
import br.com.cwi.redesocial.service.usuario.SalvarUsuarioNovo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class SalvarUsuarioTeste {
    @Mock
    private IUsuarioRepository repository;

    @Mock
    private BuscarUsuarioPorEmail buscarUsuarioPorEmail;

    @Captor
    ArgumentCaptor<Usuario> captadorDeUsuario;

    @InjectMocks
    private SalvarUsuarioNovo tested;


    @Test(expected = IllegalArgumentException.class)
    public void deveInformarUsuarioExistente() {


        Usuario usuario = new Usuario();
        String email = "email@email.com.br";
        usuario.setNome("Nome");
        usuario.setSenha("123");
        usuario.setDataNascimento(LocalDate.now());
        usuario.setEmail(email);
        Mockito.when(buscarUsuarioPorEmail.buscarPorEmail(email)).thenReturn(usuario);

        tested.salvarUsuario(usuario);

    }


    @Test
    public void deveSalvarUsuario() {

        Usuario usuario = new Usuario();
        String email = "email@email.com.br";
        usuario.setNome("Nome");
        usuario.setSenha("123");
        usuario.setDataNascimento(LocalDate.now());
        usuario.setEmail(email);
        Mockito.when(buscarUsuarioPorEmail.buscarPorEmail(email)).thenReturn(null);

        tested.salvarUsuario(usuario);

        Mockito.verify(repository, Mockito.times(1)).save(captadorDeUsuario.capture());

        Assert.assertEquals(email, captadorDeUsuario.getValue().getEmail());
    }



    }
