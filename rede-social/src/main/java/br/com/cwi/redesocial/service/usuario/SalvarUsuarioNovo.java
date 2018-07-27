package br.com.cwi.redesocial.service.usuario;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
@Service
public class SalvarUsuarioNovo {

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private BuscarUsuarioPorEmail buscarUsuarioPorEmail;

    @Autowired
    private CriptografarSenha criptografarSenha;

    public void salvarUsuario(Usuario usuario){

        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if(Objects.isNull(usuario.getNome()) || usuario.getNome().isEmpty()){
            throw new IllegalArgumentException("Nome deve ser informado");
        }
        if(Objects.isNull(usuario.getSenha()) || usuario.getSenha().isEmpty()){
            throw new IllegalArgumentException("Senha deve ser informada");
        }

        if(usuario.getDataNascimento().isBefore(LocalDate.now().minusYears(200))){
            throw new IllegalArgumentException("Usuario fora dos limites de idade");
        }
        usuario.setSenha(criptografarSenha.cifrar(usuario.getSenha()));
        repository.save(usuario);
    }
}
