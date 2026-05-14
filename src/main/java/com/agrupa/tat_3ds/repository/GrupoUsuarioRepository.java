package com.agrupa.tat_3ds.repository;

import com.agrupa.tat_3ds.models.Grupo;
import com.agrupa.tat_3ds.models.GrupoUsuario;
import com.agrupa.tat_3ds.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuario, Integer> {
    List<GrupoUsuario> findByGrupo(Grupo grupo);
    List<GrupoUsuario> findByUsuario(Usuario usuario);
    Optional<GrupoUsuario> findByUsuarioAndGrupo(Usuario usuario, Grupo grupo);
}
