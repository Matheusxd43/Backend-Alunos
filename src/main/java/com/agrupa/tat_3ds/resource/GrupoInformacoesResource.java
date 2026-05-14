package com.agrupa.tat_3ds.resource;

import com.agrupa.tat_3ds.dto.GrupoInformacoesDTO;
import com.agrupa.tat_3ds.service.GrupoInformacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoInformacoesResource {

    @Autowired
    private GrupoInformacoesService grupoInformacoesService;

    @GetMapping("/{id}")
    public ResponseEntity<GrupoInformacoesDTO> getGrupos(@PathVariable ("id") Integer idGrupo) {
        GrupoInformacoesDTO dto = grupoInformacoesService.getInformacoes(idGrupo);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<GrupoInformacoesDTO>> getGrupos() {
        List<GrupoInformacoesDTO> dto = grupoInformacoesService.getInformacoesGrupos();
        return ResponseEntity.ok(dto);
    }
}
