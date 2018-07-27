package br.com.cwi.redesocial.web;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.service.usuario.*;
import br.com.cwi.redesocial.web.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/usuario")
public class UsuarioController {

    @Autowired
    private SalvarUsuarioNovo salvarUsuarioNovo;

    @Autowired
    private BuscarUsuarioPorEmail buscarUsuarioPorEmail;

    @Autowired
    private BuscarUsuarioPorId buscarPorId;

    @Autowired
    private BuscarUsuarioLogado buscarUsuarioLogado;

    @Autowired
    BuscarUsuarios buscarUsuarios;

    @Autowired
    DeletarUsuario deletarUsuario;

    @GetMapping("/listar/{pagina}")
    public List<UsuarioDto> listar(@PathVariable("pagina") int pagina) {
        return buscarUsuarios.buscar(pagina);
    }

    @GetMapping("/buscarPorEmail")
    public Usuario buscarPorEmail(@RequestParam("email") String email) {
        return buscarUsuarioPorEmail.buscarPorEmail(email);
    }

    @GetMapping("/buscarLogado")
    public Usuario buscarUsuarioLogado() {
        return buscarUsuarioLogado.buscarLogado();
    }

    @GetMapping("/{id}")
    public UsuarioDto buscarPorId(@PathVariable("id") Long id) {
        Usuario usuario = buscarPorId.buscarPorId(id);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setImagem(usuario.getImagem());

        return usuarioDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody Usuario usuario) {
        salvarUsuarioNovo.salvarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id) {
        deletarUsuario.deletarUsuario(id);

    }





}
