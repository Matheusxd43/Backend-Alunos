package com.agrupa.tat_3ds.repository;

import com.agrupa.tat_3ds.models.Grupo;
import com.agrupa.tat_3ds.models.TrabalhoEmGrupo;
import com.agrupa.tat_3ds.models.GrupoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    List<Grupo> findByTrabalhoEmGrupo(TrabalhoEmGrupo trabalho);
}
