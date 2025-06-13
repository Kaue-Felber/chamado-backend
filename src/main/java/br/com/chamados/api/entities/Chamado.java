package br.com.chamados.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CHAMADO")
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChamado;

    private String tipoChamado;
    private Date dtCadastro;
    private String descricaoProblema;

    @Column(name = "ID_ATENDENTE_CHAMADO")
    private Long idAtendenteChamado;
}
