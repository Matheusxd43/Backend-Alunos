package com.agrupa.tat_3ds.repository;

import com.agrupa.tat_3ds.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByNomeAndTrabalhoIdTrabalho(String nome, Integer idTrabalho);
}
