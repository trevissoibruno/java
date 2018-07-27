package br.com.cwi.redesocial.web;

import br.com.cwi.redesocial.dominio.Post;
import br.com.cwi.redesocial.service.post.*;
import br.com.cwi.redesocial.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    @Autowired
    SalvarPostNovo salvarPostNovo;

    @Autowired
    BuscarPostPorId buscarPorId;

    @Autowired
    BuscarPostPorUsuario buscarPostPorUsuario;

    @Autowired
    BuscarPostsDeAmigos buscarPostsDeAmigos;


    @GetMapping("/buscar")
    public List<PostDto> listar(Pageable pageable) {
        return buscarPostPorUsuario.buscarPorIdUsuario(pageable);
    }



    @GetMapping
    public List<PostDto> listar() {
        return buscarPostsDeAmigos.buscarPostsAmigos();
    }


    @GetMapping("/{id}")
    public Post buscarPorId(@PathVariable("id") Long id) {
        return buscarPorId.buscarPorId(id);
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody Post post) {
        salvarPostNovo.salvarPost(post);
    }




}
