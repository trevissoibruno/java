package br.com.cwi.redesocial.service.usuario;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import br.com.cwi.redesocial.web.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class BuscarUsuarios {

    @Autowired
    private IUsuarioRepository repository;

    public List<UsuarioDto> buscar(int pagina) {

        if (Objects.isNull(pagina)) {
            throw new IllegalArgumentException("Pagina invalida");
        }

        List<UsuarioDto> usuariosSemSenha = new LinkedList<>();

        List<Usuario> usuariosBuscados = new LinkedList<>();

        usuariosBuscados = repository.findAll(new PageRequest(pagina,5)).getContent();

        for (Usuario u: usuariosBuscados) {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setImagem(u.getImagem());
            usuarioDto.setNome(u.getNome());
            usuarioDto.setEmail(u.getEmail());
            usuariosSemSenha.add(usuarioDto);
        }
        return usuariosSemSenha;
    }

}
