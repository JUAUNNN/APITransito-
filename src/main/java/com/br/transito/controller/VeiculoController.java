package com.br.transito.controller;
import com.br.transito.assembler.VeiculoAssembler;
import com.br.transito.domain.model.Veiculo;
import com.br.transito.domain.repository.VeiculoRepository;
import com.br.transito.domain.service.ApreensaoVeiculoService;
import com.br.transito.domain.service.RegistroVeiculoService;
import com.br.transito.model.VeiculoModel;
import com.br.transito.model.input.VeiculoInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;
    private final ApreensaoVeiculoService apreensaoVeiculoService;
    private final VeiculoAssembler veiculoAssembler;

    @GetMapping
    public List<VeiculoModel> listar(){
        return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public VeiculoModel adicionarCarro(@Valid @RequestBody VeiculoInput veiculoInput) {
        Veiculo novoVeiculo = veiculoAssembler.toEntity(veiculoInput);
        Veiculo veiculoCadastro = registroVeiculoService.cadastrar(novoVeiculo);

        return veiculoAssembler.toModel(veiculoCadastro);

   }

   @PutMapping("/{veiculoId}/apreensao")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void apreender(@PathVariable Long veiculoId) {
        apreensaoVeiculoService.apreender(veiculoId);
   }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerApreender(@PathVariable Long veiculoId) {
        apreensaoVeiculoService.removerApreencao(veiculoId);
    }

}
