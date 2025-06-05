package com.br.transito.controller;

import com.br.transito.domain.exception.NegocioException;
import com.br.transito.domain.model.Proprietario;
import com.br.transito.domain.repository.ProprietarioRepository;
import com.br.transito.domain.service.CadastroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final CadastroProprietarioService cadastroProprietarioService;
    private final ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar(){
      return proprietarioRepository.findAll();
        //return  proprietarioRepository.findByNome("João Paulo Vieira");--Teste de costumização.
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar (@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario cadastrar (@Valid @RequestBody Proprietario proprietario) {
        return cadastroProprietarioService.salvar(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar (@Valid @PathVariable Long proprietarioId, @RequestBody Proprietario proprietario) {
        if (!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }
        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = cadastroProprietarioService.salvar(proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> deletar (@PathVariable Long proprietarioId) {
        if (!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }
        cadastroProprietarioService.excluir(proprietarioId);
        return ResponseEntity.noContent().build();
    }
}
