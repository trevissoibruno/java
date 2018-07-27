package br.com.cwi.redesocial.service.usuario;

import br.com.cwi.redesocial.dominio.Usuario;
import br.com.cwi.redesocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioLogado {

    @Autowired
    private BuscarUsuarioPorId buscarUsuarioPorId;

    public Usuario buscarLogado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return buscarUsuarioPorId.buscarPorId(userPrincipal.getId());
    }
}
