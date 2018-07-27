package br.com.cwi.redesocial.service.amizade;

import br.com.cwi.redesocial.dominio.Amizade;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.enumeration.StatusSolicitacao;
import br.com.cwi.redesocial.repository.IAmizadeRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarAmizadesAceitas {

    @Autowired
    private IAmizadeRepository repository;

    @Autowired
    private BuscarUsuarioLogado buscarUsuarioLogado;


    public List<Amizade> buscarAmizades(StatusSolicitacao situacao){
        Usuario usuario = buscarUsuarioLogado.buscarLogado();
        return repository.findAllBySituacaoAndUsuario_Id(situacao,usuario.getId());

    }
}
