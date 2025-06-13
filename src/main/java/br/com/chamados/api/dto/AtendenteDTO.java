package br.com.chamados.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class AtendenteDTO {
    private String login;
    private String senha;
}
