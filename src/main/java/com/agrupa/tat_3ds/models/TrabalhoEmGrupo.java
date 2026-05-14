package com.agrupa.tat_3ds.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "trabalho_em_groupo")
public class TrabalhoEmGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabalho")
    private Integer idTrabalho;

    @Column(length = 1000)
    private String descricao;

    @Column(name = "hash_para_acesso", length = 200)
    private String hashParaAcesso;

    private Integer quantidadePessoas;

    private Integer quantidadeGrupos;

}