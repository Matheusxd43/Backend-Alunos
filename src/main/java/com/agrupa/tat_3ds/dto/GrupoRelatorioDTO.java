package com.agrupa.tat_3ds.dto;

import java.util.List;

public record GrupoRelatorioDTO(
        String identificador,
        Integer totalIntegrantes,
        Integer capacidadeMaxima,
        List<String> alunos
){}
