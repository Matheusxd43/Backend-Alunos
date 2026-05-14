package com.agrupa.tat_3ds.form;

import lombok.Getter;

import java.util.List;

@Getter
public class RegistroForm {

    public String descricaoAtividade;

    public Integer quantidadeTotalAlunosSala;

    public List<GrupoForm> grupoForm;
}
