package com.br.transito.domain.service;

import com.br.transito.domain.model.Autuacao;
import com.br.transito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {

    private RegistroVeiculoService registroVeiculoService;

    @Transactional
    public Autuacao registar(Long veiculoId, Autuacao novaAutuacao) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);

        return veiculo.adicionarAutuacao(novaAutuacao);
    }
}
