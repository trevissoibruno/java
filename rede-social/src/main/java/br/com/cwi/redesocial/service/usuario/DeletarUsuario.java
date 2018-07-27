package br.com.cwi.redesocial.service.usuario;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.exception.UsuarioNaoCadastrado;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarUsuario {

    @Autowired
    private IUsuarioRepository repository;

    public void deletarUsuario(Long id){
        Usuario usuario = repository
                .findById(id)
                .orElseThrow(() -> new UsuarioNaoCadastrado());

        repository.delete(usuario);
    }
}
