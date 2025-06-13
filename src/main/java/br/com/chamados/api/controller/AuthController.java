package br.com.chamados.api.controller;

import br.com.chamados.api.dto.AtendenteDTO;
import br.com.chamados.api.dto.UsuarioLogado;
import br.com.chamados.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JdbcTemplate jdbc;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AtendenteDTO dto){
        System.out.println(dto.getLogin().toUpperCase());
        try{
            Long idUsuario = jdbc.queryForObject(
                    "SELECT pc_bf_authentication.fn_validate_login_react(?, ?) FROM dual",
                    Long.class,
                    dto.getLogin().toUpperCase(),
                    dto.getSenha().toUpperCase()
            );

            if (idUsuario != null && idUsuario > 0){
                String token = JwtUtil.gerarToken(dto.getLogin(), idUsuario);
                return ResponseEntity.ok(new UsuarioLogado(idUsuario, dto.getLogin(), token));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login inválido");

        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }
}
