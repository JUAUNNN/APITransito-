package com.br.transito.domain.service;

import com.br.transito.domain.exception.NegocioException;
import com.br.transito.domain.model.Proprietario;
import com.br.transito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Service
public class CadastroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean emailEmUso =  proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();
        if (emailEmUso){
            throw new NegocioException("Já existe um proprietário cadastrado com este e-mail!");
        }
        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }

}
