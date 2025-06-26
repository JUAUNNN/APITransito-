package com.br.transito.domain.service;

import com.br.transito.domain.exception.NegocioException;
import com.br.transito.domain.model.Proprietario;
import com.br.transito.domain.model.StatusVeiculo;
import com.br.transito.domain.model.Veiculo;
import com.br.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;


@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final CadastroProprietarioService cadastroProprietarioService;

    public Veiculo buscar(Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new NegocioException("Veiculo não encontrado"));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        if (novoVeiculo.getId() != null) {
            throw new NegocioException("Veiculo a ser cadastrado não deve possuir um codigo");
        }

        boolean placaCadastrada = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                        .filter(v -> !v.equals(novoVeiculo))
                                .isPresent();
        if (placaCadastrada) {
            throw new NegocioException("Já existe um veiculo cadastrado com esta placa!");
        }

        Proprietario proprietario = cadastroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }
}
