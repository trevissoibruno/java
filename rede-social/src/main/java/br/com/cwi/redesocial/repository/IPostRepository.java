package br.com.cwi.redesocial.repository;

import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.dominio.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface IPostRepository extends Repository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    Optional<Post> findById(Long id);

    Post save(Post post);

    void delete(Post post);

    Page<Post> findByUsuario_Id(Long id,Pageable pageable);

    List<Post> findAllByUsuario(List<Usuario> usuarios);


}
