package br.com.cwi.redesocial;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarios;
import br.com.cwi.redesocial.web.dto.UsuarioDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuariosTest {


    @InjectMocks
    private BuscarUsuarios tested;

    @Mock
    private IUsuarioRepository repository;


    @Test
    public void buscaUsuarios() {

        List<Usuario> usuarios = new LinkedList<>();

        Long id1 = 1L;
        Long id2 = 2L;

        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        usuario1.setId(id1);
        usuario2.setId(id2);
        usuarios.add(usuario1);
        usuarios.add(usuario2);


        repository.save(usuario1);
        repository.save(usuario2);

        List<UsuarioDto> usuariosRetornados =  tested.buscar(0);

        Assert.assertEquals(true,usuariosRetornados.contains(usuario1));

    }


}
