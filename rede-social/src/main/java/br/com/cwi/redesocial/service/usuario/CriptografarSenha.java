package br.com.cwi.redesocial.service.usuario;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class CriptografarSenha {

    public String cifrar(String originalString){
        String sha256hex = Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();

        return sha256hex;
    }
}
