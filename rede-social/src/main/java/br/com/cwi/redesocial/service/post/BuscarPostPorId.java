package br.com.cwi.redesocial.service.post;

import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.exception.PostNaoCadastrado;
import br.com.cwi.redesocial.exception.UsuarioNaoCadastrado;
import br.com.cwi.redesocial.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarPostPorId {

    @Autowired
    private IPostRepository repository;

    public Post buscarPorId(Long id) {

        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("id inválido");
        }

        return repository
                .findById(id)
                .orElseThrow(() -> new PostNaoCadastrado());
    }
}
