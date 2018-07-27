package br.com.cwi.redesocial.web;

import br.com.cwi.redesocial.dominio.Comentario;
import br.com.cwi.redesocial.service.comentario.BuscarComentarioPorIdPost;
import br.com.cwi.redesocial.service.comentario.SalvarComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comentario")
@RestController
public class ComentarioController {

    @Autowired
    private SalvarComentario salvarComentario;

    @Autowired
    private BuscarComentarioPorIdPost buscarComentarioPorIdPost;

    @GetMapping("/buscarPorPost/{idPost}")
    public List<Comentario> buscarPorPost(@PathVariable("idPost") Long idPost) {
        return buscarComentarioPorIdPost.buscarComentario(idPost);
    }

    @PostMapping("/{idPost}")
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody Comentario comentario,@PathVariable("idPost") Long idPost) {
        salvarComentario.salvarComentario(comentario,idPost);
    }
}
