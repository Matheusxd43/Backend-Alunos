package com.agrupa.tat_3ds.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoInformacoesDTO {

    private Integer idGrupo;
    private List<PosicaoUsuariosDTO> usuarios;
    private String nomeGrupo;
    private Integer qtdeUsuarios;
    private Integer qtdePessoas;
    private String hash;
    private String nomeAtividade;
    private Integer idAtividade;
}