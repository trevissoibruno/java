package br.com.cwi.redesocial.service.avaliacao;

import br.com.cwi.redesocial.dominio.Avaliacao;
import br.com.cwi.redesocial.repository.IAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BuscarAvaliacaoPorIdPost {

    @Autowired
    private IAvaliacaoRepository repository;

    public List<Avaliacao> buscarAvaliacao(Long idPost){

        if (Objects.isNull(idPost)) {
            throw new IllegalArgumentException("Id do Post Invalido");
        }
        return repository.findByPost_Id(idPost);
    }

}

