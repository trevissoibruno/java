package br.com.cwi.redesocial.web;

import br.com.cwi.redesocial.dominio.Amizade;
import br.com.cwi.redesocial.enumeration.StatusSolicitacao;
import br.com.cwi.redesocial.service.amizade.AceitarAmizade;
import br.com.cwi.redesocial.service.amizade.BuscarAmizadesAceitas;
import br.com.cwi.redesocial.service.amizade.SalvarNovaAmizade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/amizade")
@RestController
public class AmizadeController {

    @Autowired
    private SalvarNovaAmizade salvarAmizade;

    @Autowired
    private AceitarAmizade aceitarAmizade;

    @Autowired
    private BuscarAmizadesAceitas buscarAmizadesAceitas;


    @PostMapping("/serAmigo/{idAmigo}")
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@PathVariable("idAmigo") Long idAmigo) {
        salvarAmizade.salvarAmizade(idAmigo);
    }

    @PostMapping("/aceitar/{idAmigo}/{idUsuario}")
    @ResponseStatus(HttpStatus.CREATED)
    public void aceitar(@PathVariable("idAmigo") Long idAmigo,@PathVariable("idUsuario") Long idUsuario) {
        aceitarAmizade.acitarAmizade(idAmigo,idUsuario);
    }

    @GetMapping("/amizades/aceitas")
    public List<Amizade> listar() {
        return buscarAmizadesAceitas.buscarAmizades(StatusSolicitacao.ACEITO);
    }
}
