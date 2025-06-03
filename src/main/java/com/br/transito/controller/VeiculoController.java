package com.br.transito.controller;
import com.br.transito.domain.model.Veiculo;
import com.br.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listar(){
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Veiculo adicionarCarro(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
   }

}
