package br.com.cwi.redesocial.web.login;


import br.com.cwi.redesocial.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/login")
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {

        String email = request.getEmail();
        String senha = request.getSenha();

        String token = authenticationService.authenticate(email, senha);

        return new LoginResponse(token);

    }

}
