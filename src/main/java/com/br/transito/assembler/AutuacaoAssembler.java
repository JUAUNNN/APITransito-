package com.br.transito.assembler;

import com.br.transito.domain.model.Autuacao;
import com.br.transito.domain.model.AutuacaoModel;
import com.br.transito.model.input.AutuacaoInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AutuacaoAssembler {

    private final ModelMapper modelMapper;

    public Autuacao toEntity(AutuacaoInput autuacaoInput) {
        return modelMapper.map(autuacaoInput, Autuacao.class);
    }

    public AutuacaoModel toModel(Autuacao autuacoes) {
        return modelMapper.map(autuacoes, AutuacaoModel.class);
    }
    public List<AutuacaoModel> toCollectionModel(List<Autuacao> autuacoes) {
        return autuacoes.stream()
                .map(this::toModel)
                .toList();
    }
}
