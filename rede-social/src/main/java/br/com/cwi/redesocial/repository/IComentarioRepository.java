package br.com.cwi.redesocial.repository;

import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.dominio.Usuario;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface IComentarioRepository  extends Repository<Comentario, Long> {
    List<Comentario> findByPost_Id(Long id);

    Comentario save(Comentario comentario);


}
