package br.com.cwi.redesocial.web.login;

import lombok.Data;

@Data
public class LoginRequest {

    private final String email;

    private final String senha;

}
