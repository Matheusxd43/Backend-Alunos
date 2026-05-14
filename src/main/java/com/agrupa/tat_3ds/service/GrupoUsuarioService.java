package com.agrupa.tat_3ds.service;

import com.agrupa.tat_3ds.models.Grupo;
import com.agrupa.tat_3ds.models.GrupoUsuario;
import com.agrupa.tat_3ds.models.Usuario;
import com.agrupa.tat_3ds.repository.GrupoRepository;
import com.agrupa.tat_3ds.repository.GrupoUsuarioRepository;
import com.agrupa.tat_3ds.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoUsuarioRepository grupoUsuarioRepository;


    @Transactional
    public GrupoUsuario moverUsuario(Integer idUsuario, Integer idGrupo, Integer posicao) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        Optional<Grupo> grupoOpt = grupoRepository.findById(idGrupo);

        if (usuarioOpt.isEmpty() || grupoOpt.isEmpty()) {
            return null;
        }

        Usuario usuario = usuarioOpt.get();
        Grupo grupoDestino = grupoOpt.get();

        List<GrupoUsuario> existentes = grupoUsuarioRepository.findByUsuario(usuario);

        List<GrupoUsuario> existentesNoMesmoGrupo = existentes.stream()
                .filter(g -> g.getGrupo() != null
                        && g.getGrupo().getTrabalhoEmGrupo() != null
                        && g.getGrupo().getTrabalhoEmGrupo().getIdTrabalho()
                        .equals(grupoDestino.getTrabalhoEmGrupo().getIdTrabalho()))
                .toList();

        Optional<GrupoUsuario> talvezMesmoPar =
                grupoUsuarioRepository.findByUsuarioAndGrupo(usuario, grupoDestino);

        if (talvezMesmoPar.isPresent()) {
            GrupoUsuario gp = talvezMesmoPar.get();
            gp.setPosicao(posicao);
            return grupoUsuarioRepository.save(gp);
        }

        for (GrupoUsuario antigo : existentesNoMesmoGrupo) {
            antigo.setUsuario(null);
            grupoUsuarioRepository.save(antigo);
        }

        Optional<GrupoUsuario> vaga = grupoUsuarioRepository.findByGrupo(grupoDestino)
                .stream()
                .filter(g -> g.getUsuario() == null)
                .findFirst();

        if (vaga.isEmpty()) return null;

        GrupoUsuario gu = vaga.get();
        gu.setUsuario(usuario);

        usuario.setIdGrupo(grupoDestino.getIdGrupo());
        usuarioRepository.save(usuario);

        return grupoUsuarioRepository.save(gu);
    }


    @Transactional
    public int resetPosicoesAll() {

        List<GrupoUsuario> todos = grupoUsuarioRepository.findAll();

        int count = 0;

        for (GrupoUsuario gu : todos) {
            if (gu.getUsuario() != null) {
                gu.setUsuario(null);
                count++;
            }
        }

        grupoUsuarioRepository.saveAll(todos);

        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario u : usuarios) {
            u.setIdGrupo(null);
        }
        usuarioRepository.saveAll(usuarios);

        return count;
    }

    @Transactional
    public int resetPosicoesGrupo(Integer idGrupo) {

        Optional<Grupo> grupoOpt = grupoRepository.findById(idGrupo);

        if (grupoOpt.isEmpty()) return 0;

        List<GrupoUsuario> lista = grupoUsuarioRepository.findByGrupo(grupoOpt.get());

        int count = 0;

        for (GrupoUsuario gu : lista) {
            if (gu.getUsuario() != null) {
                gu.setUsuario(null);
                count++;
            }
        }

        grupoUsuarioRepository.saveAll(lista);

        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario u : usuarios) {
            if (idGrupo.equals(u.getIdGrupo())) {
                u.setIdGrupo(null);
            }
        }
        usuarioRepository.saveAll(usuarios);

        return count;
    }

}