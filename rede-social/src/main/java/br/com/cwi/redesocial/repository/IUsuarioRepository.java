package br.com.cwi.redesocial.repository;

import br.com.cwi.redesocial.dominio.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface IUsuarioRepository extends Repository<Usuario, Long> {

    Page<Usuario> findAll(Pageable pageable);

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    void delete(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

}
