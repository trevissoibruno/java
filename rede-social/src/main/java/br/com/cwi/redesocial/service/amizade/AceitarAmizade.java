package br.com.cwi.redesocial.service.amizade;

import br.com.cwi.redesocial.dominio.Amizade;
import br.com.cwi.redesocial.enumeration.StatusSolicitacao;
import br.com.cwi.redesocial.repository.IAmizadeRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AceitarAmizade {

    @Autowired
    private IAmizadeRepository repository;

    @Autowired
    private BuscarAmizadePorId buscarAmizadePorId;

    @Autowired
    private BuscarUsuarioPorId buscarUsuarioPorId;

    public void acitarAmizade(Long idAmigo, Long idUsuario){

        Amizade amizade1 = buscarAmizadePorId.buscarPorId(idAmigo,idUsuario);

        Amizade amizade2 = buscarAmizadePorId.buscarPorId(idUsuario,idAmigo);

        amizade1.setSituacao(StatusSolicitacao.ACEITO);
        amizade2.setSituacao(StatusSolicitacao.ACEITO);

        repository.save(amizade1);
        repository.save(amizade2);
    }
}
