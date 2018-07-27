package br.com.cwi.redesocial.repository;

import br.com.cwi.redesocial.dominio.Avaliacao;
import br.com.cwi.redesocial.dominio.Usuario;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface IAvaliacaoRepository extends Repository<Avaliacao, Long> {

    Avaliacao save(Avaliacao avaliacao);

    List<Avaliacao> findByPost_Id(Long id);

    Avaliacao findByPost_IdAndUsuario_Id(Long idPost,Long idUsuario);

    void delete(Avaliacao avaliacao);

    Optional<Avaliacao> findById(Long id);
}
