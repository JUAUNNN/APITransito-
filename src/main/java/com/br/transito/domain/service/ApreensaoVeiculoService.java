package com.br.transito.domain.service;

import com.br.transito.domain.model.StatusVeiculo;
import com.br.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {

    private final RegistroVeiculoService registroVeiculoService;

    @Transactional
    public void apreender(Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.apreender();
    }

    @Transactional
    public void removerApreencao(Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        veiculo.removerApreencao();
    }

}
