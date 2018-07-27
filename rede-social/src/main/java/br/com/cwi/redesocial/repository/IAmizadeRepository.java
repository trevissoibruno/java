package br.com.cwi.redesocial.repository;
import br.com.cwi.redesocial.dominio.Amizade;

import br.com.cwi.redesocial.enumeration.StatusSolicitacao;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface IAmizadeRepository  extends Repository<Amizade, Long> {

    Amizade save(Amizade amizade);

    Optional<Amizade> findByUsuario_IdAndAmigo_Id(Long idUsuario, Long idAmigo);

    List<Amizade> findAllBySituacaoAndUsuario_Id(StatusSolicitacao solicitacao, Long id);



}
