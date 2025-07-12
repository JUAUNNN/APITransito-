package com.br.transito.controller;

import com.br.transito.assembler.AutuacaoAssembler;
import com.br.transito.domain.model.Autuacao;
import com.br.transito.domain.model.AutuacaoModel;
import com.br.transito.domain.model.Veiculo;
import com.br.transito.domain.service.RegistroAutuacaoService;
import com.br.transito.domain.service.RegistroVeiculoService;
import com.br.transito.model.input.AutuacaoInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final RegistroAutuacaoService registroAutuacaoService;
    private final AutuacaoAssembler autuacaoAssembler;
    private final RegistroVeiculoService registroVeiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar(@PathVariable Long veiculoId,
                                   @Valid @RequestBody AutuacaoInput autuacaoInput) {
        Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada =
                registroAutuacaoService.registar(veiculoId, novaAutuacao);
        return autuacaoAssembler.toModel(autuacaoRegistrada);
    }

    @GetMapping
    public List<AutuacaoModel> listar(@PathVariable Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return autuacaoAssembler.toCollectionModel(veiculo.getAuttuacoes());
    }
}
