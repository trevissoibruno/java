package br.com.cwi.redesocial.web;

import br.com.cwi.redesocial.dominio.Avaliacao;
import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.service.avaliacao.BuscarAvaliacaoPorIdPost;
import br.com.cwi.redesocial.service.avaliacao.DeletarAvaliacao;
import br.com.cwi.redesocial.service.avaliacao.SalvarAvaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/avaliacao")
@RestController
public class AvaliacaoController {

    @Autowired
    private DeletarAvaliacao deletarAvaliacao;

    @Autowired
    private SalvarAvaliacao salvarAvaliacao;

    @Autowired
    private BuscarAvaliacaoPorIdPost buscarAvaliacaoPorIdPost;


    @GetMapping("/buscarPorPost/{idPost}")
    public List<Avaliacao> buscarPorPost(@PathVariable("idPost") Long idPost) {
        return buscarAvaliacaoPorIdPost.buscarAvaliacao(idPost);
    }


    @PostMapping("/avaliar/{idPost}")
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@PathVariable("idPost") Long idPost) {
        salvarAvaliacao.salvarAvaliacao(idPost);
    }


    @DeleteMapping("/{idPost}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("idPost") Long idPost) {
        deletarAvaliacao.deletarAvaliacao(idPost);

    }
}
