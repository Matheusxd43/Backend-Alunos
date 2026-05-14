package com.agrupa.tat_3ds.service;

import com.agrupa.tat_3ds.dto.UsuarioCadastroDTO;
import com.agrupa.tat_3ds.models.TrabalhoEmGrupo;
import com.agrupa.tat_3ds.models.Usuario;
import com.agrupa.tat_3ds.repository.TrabalhoRepository;
import com.agrupa.tat_3ds.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TrabalhoRepository trabalhoRepository;

    public Usuario cadastrar(UsuarioCadastroDTO dto) {

        String nomeFormatado = dto.getNome().trim().toLowerCase() + "." +
                String.valueOf(dto.getSobrenome()).toLowerCase();
        TrabalhoEmGrupo trabalho = trabalhoRepository.findByHashParaAcesso(dto.getHash())
                .orElseThrow(() -> new RuntimeException("Trabajo no encontrado"));

        if (usuarioRepository.existsByNomeAndTrabalhoIdTrabalho(
                nomeFormatado, trabalho.getIdTrabalho())) {
            throw new RuntimeException("Ya existe un usuario con ese nombre.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nomeFormatado);
        usuario.setTrabalho(trabalho);

        return usuarioRepository.save(usuario);
    }
}
