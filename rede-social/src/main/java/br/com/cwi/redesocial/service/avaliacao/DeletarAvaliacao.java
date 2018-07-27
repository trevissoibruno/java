package br.com.cwi.redesocial.service.avaliacao;

import br.com.cwi.redesocial.dominio.Avaliacao;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.exception.AvaliacaoNaoCadastrado;
import br.com.cwi.redesocial.repository.IAvaliacaoRepository;
import br.com.cwi.redesocial.service.usuario.BuscarUsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarAvaliacao {


    @Autowired
    private IAvaliacaoRepository repository;

    @Autowired
    private BuscarUsuarioLogado buscarUsuarioLogado;


    public void deletarAvaliacao(Long idPost){
        Usuario usuario = buscarUsuarioLogado.buscarLogado();

        Avaliacao avaliacao = repository.findByPost_IdAndUsuario_Id(idPost,usuario.getId());

        repository.delete(avaliacao);
    }
}
