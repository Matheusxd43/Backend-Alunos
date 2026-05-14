package com.agrupa.tat_3ds.dto;

import java.util.List;

public record RelatorioTrabalhoDTO(
        String nomeAtividade,
        String dataGeracao,
        Integer totalCelulas,
        Integer totalAlunos,
        List<GrupoRelatorioDTO> grupos
) {}

