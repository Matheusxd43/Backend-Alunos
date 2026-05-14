package com.agrupa.tat_3ds.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrupo;

    @ManyToOne
    @JoinColumn(name = "id_trabalho")
    private TrabalhoEmGrupo trabalhoEmGrupo;

    @Column(length = 20)
    private String descricao;

    @Column(length = 200)
    private String atividade;
}