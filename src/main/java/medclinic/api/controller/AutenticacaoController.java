package medclinic.api.controller;

import jakarta.validation.Valid;
import medclinic.api.dto.DadosAutenticacao;
import medclinic.api.infra.security.DadosTokenJWT;
import medclinic.api.infra.security.TokenService;
import medclinic.api.models.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        System.out.println("entrou?");
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
