package br.com.cwi.redesocial.service.comentario;

import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.repository.IComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarComentarioPorIdPost {

    @Autowired
    private IComentarioRepository repository;

    public List<Comentario> buscarComentario(Long idPost){
        return repository.findByPost_Id(idPost);
    }
}
