package br.com.cwi.redesocial.service.usuario;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.exception.UsuarioNaoCadastrado;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarUsuarioPorId {

    @Autowired
    private IUsuarioRepository repository;

    public Usuario buscarPorId(Long id) {

        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("id inválido");
        }

        return repository
                .findById(id)
                .orElseThrow(() -> new UsuarioNaoCadastrado());
    }
}
