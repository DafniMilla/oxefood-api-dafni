package br.com.ifpe.oxefood.api.acesso;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.seguranca.JwtService;

@RestController //Indica que essa classe é um controlador REST — ela recebe requisições HTTP e devolve respostas em formato JSON.
@RequestMapping("/api/auth")
@CrossOrigin //Permite requisições vindas de outro domínio
public class AuthenticationController {

    //Atributos (injeção de dependência)
    private final JwtService jwtService; //responsável por gerar tokens JWT
    
    private UsuarioService usuarioService; //responsável por validar o usuário e senha.

    public AuthenticationController(JwtService jwtService, UsuarioService usuarioService) {

        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Map<Object, Object> signin(@RequestBody AuthenticationRequest data) {
    
        Usuario authenticatedUser = usuarioService.authenticate(data.getUsername(), data.getPassword());  //Autentica o usuário

        String jwtToken = jwtService.generateToken(authenticatedUser); //Gera o token JWT

        //Monta a resposta
        Map<Object, Object> loginResponse = new HashMap<>();
        loginResponse.put("username", authenticatedUser.getUsername());
        loginResponse.put("token", jwtToken);
        loginResponse.put("tokenExpiresIn", jwtService.getExpirationTime());

        return loginResponse;
    }    
}