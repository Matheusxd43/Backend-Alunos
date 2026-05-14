package com.agrupa.tat_3ds.repository;

import com.agrupa.tat_3ds.models.TrabalhoEmGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabalhoRepository extends JpaRepository<TrabalhoEmGrupo, Integer> {

    Optional<TrabalhoEmGrupo> findByHashParaAcesso(String hashParaAcesso);
}
