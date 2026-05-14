package com.agrupa.tat_3ds.service;

import com.agrupa.tat_3ds.form.GrupoForm;
import com.agrupa.tat_3ds.form.RegistroForm;
import com.agrupa.tat_3ds.models.Grupo;
import com.agrupa.tat_3ds.models.GrupoUsuario;
import com.agrupa.tat_3ds.models.TrabalhoEmGrupo;
import com.agrupa.tat_3ds.repository.GrupoRepository;
import com.agrupa.tat_3ds.repository.GrupoUsuarioRepository;
import com.agrupa.tat_3ds.repository.TrabalhoRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrabalhoService {

    private final TrabalhoRepository trabalhoRepository;
    private final GrupoRepository grupoRepository;
    private final GrupoUsuarioRepository grupoUsuarioRepository;

    public TrabalhoService(TrabalhoRepository trabalhoRepository, GrupoRepository grupoRepository, GrupoUsuarioRepository grupoUsuarioRepository) {
        this.trabalhoRepository = trabalhoRepository;
        this.grupoRepository = grupoRepository;
        this.grupoUsuarioRepository = grupoUsuarioRepository;
    }

    @Transactional
    public void cadastrarTrabalhoGrupos(RegistroForm registroForm) {
        TrabalhoEmGrupo atividadeSalva = RegistrarAtividade(registroForm);
        List<Grupo> registroGruposSalvos = RegistrarGrupo(registroForm.grupoForm, atividadeSalva);
        RegistrarPosicoes(registroGruposSalvos, registroForm.grupoForm);

    }

    @Transactional
    protected TrabalhoEmGrupo RegistrarAtividade(RegistroForm registroForm){
        String dados = registroForm.getDescricaoAtividade() + System.currentTimeMillis();
        TrabalhoEmGrupo registroAtividade = new TrabalhoEmGrupo();
        registroAtividade.setHashParaAcesso(DigestUtils.sha256Hex(dados));
        registroAtividade.setDescricao(registroForm.getDescricaoAtividade());
        registroAtividade.setQuantidadeGrupos(registroForm.getGrupoForm().size());
        registroAtividade.setQuantidadePessoas(registroForm.getQuantidadeTotalAlunosSala());
        this.trabalhoRepository.save(registroAtividade);
        return registroAtividade;
    }

    @Transactional
    protected List<Grupo> RegistrarGrupo(List<GrupoForm> grupoForm, TrabalhoEmGrupo atividadeSalva) {
        List<Grupo> gruposParaSalvar = grupoForm.stream()
                .map(form -> {
                    Grupo grupo = new Grupo();
                    grupo.setTrabalhoEmGrupo(atividadeSalva);
                    grupo.setDescricao(form.getNomeGrupo());
                    grupo.setAtividade(form.getAtividade());
                    return grupo;
                })
                .collect(Collectors.toList());
        this.grupoRepository.saveAll(gruposParaSalvar);
        return gruposParaSalvar;
    }

    @Transactional
    protected void RegistrarPosicoes(List<Grupo> registroGruposSalvos, List<GrupoForm> grupoForm){
        List<GrupoUsuario> posicoesParaSalvar = new ArrayList<>();

        for (int i = 0; i < registroGruposSalvos.size(); i++) {
            Grupo grupoSalvo = registroGruposSalvos.get(i);
            GrupoForm formOriginal = grupoForm.get(i);

            for (int p = 1; p <= formOriginal.getQuantidadeMaximaAluno(); p++) {
                GrupoUsuario posicaoVazia = new GrupoUsuario();
                posicaoVazia.setGrupo(grupoSalvo);
                posicaoVazia.setPosicao(p);
                posicaoVazia.setUsuario(null);
                posicoesParaSalvar.add(posicaoVazia);
            }
        }
        this.grupoUsuarioRepository.saveAll(posicoesParaSalvar);
    }

    @Transactional
    public void editarGrupo(Integer id, String novoNome) {
        this.grupoRepository.findById(id)
                .map(grupo -> {
                    grupo.setDescricao(novoNome);
                    return grupo;
                })
                .orElseThrow(() -> new RuntimeException("Não foi encontrado nenhum grupo com o id: " + id));
    }

}
