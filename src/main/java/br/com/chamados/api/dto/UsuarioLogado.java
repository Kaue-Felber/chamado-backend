package br.com.chamados.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsuarioLogado {
    private Long id;
    private String nome;
    private String token;
}
