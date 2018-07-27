package br.com.cwi.redesocial.service.amizade;

import br.com.cwi.redesocial.dominio.Amizade;
import br.com.cwi.redesocial.exception.AmizadeNaoCadastrada;
import br.com.cwi.redesocial.repository.IAmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarAmizadePorId {

    @Autowired
    private IAmizadeRepository repository;


    public Amizade buscarPorId(Long idAmigo, Long idUsuario) {

        if (Objects.isNull(idAmigo)) {
            throw new IllegalArgumentException("id Amigo invalido");
        }

        if (Objects.isNull(idUsuario)) {
            throw new IllegalArgumentException("id Usuario invalido");
        }

        return repository
                .findByUsuario_IdAndAmigo_Id(idUsuario,idAmigo)
                .orElseThrow(() -> new AmizadeNaoCadastrada());
    }
}
