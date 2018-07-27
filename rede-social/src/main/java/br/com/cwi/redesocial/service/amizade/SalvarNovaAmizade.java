package br.com.cwi.redesocial.service.amizade;

import br.com.cwi.redesocial.dominio.Amizade;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.enumeration.StatusSolicitacao;
import br.com.cwi.redesocial.repository.IAmizadeRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioPorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SalvarNovaAmizade {

    @Autowired
    private IAmizadeRepository repository;

    @Autowired
    private BuscarUsuarioPorId buscarUsuarioPorId;

    @Autowired
    private BuscarUsuarioLogado buscarUsuarioLogado;

    public void salvarAmizade(Long idAmigo){

        if (Objects.isNull(idAmigo)) {
            throw new IllegalArgumentException("Id do Amigo invalido");
        }

        Usuario amigo = buscarUsuarioPorId.buscarPorId(idAmigo);
        Usuario usuario = buscarUsuarioLogado.buscarLogado();

        Amizade amizade = new Amizade();
        Amizade amizade1 = new Amizade();

        amizade.setUsuario(usuario);
        amizade.setAmigo(amigo);

        amizade1.setAmigo(usuario);
        amizade1.setUsuario(amigo);

        amizade.setSituacao(StatusSolicitacao.PENDENTE);
        amizade1.setSituacao(StatusSolicitacao.PENDENTE);

        repository.save(amizade);
        repository.save(amizade1);
    }
}
