package br.com.cwi.redesocial.service.usuario;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.exception.UsuarioNaoCadastrado;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarUsuarioPorEmail {

    @Autowired
    private IUsuarioRepository repository;


    public Usuario buscarPorEmail(String email) {
        if (Objects.isNull(email)) {
            throw new IllegalArgumentException("email inválido");
        }

        return repository
                .findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoCadastrado());
    }
}
