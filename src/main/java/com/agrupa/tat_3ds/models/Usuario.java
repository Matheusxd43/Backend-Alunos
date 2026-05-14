package com.agrupa.tat_3ds.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_trabalho")
    private TrabalhoEmGrupo trabalho;

    @Column(length = 50)
    private String nome;

    private Integer idGrupo;
}