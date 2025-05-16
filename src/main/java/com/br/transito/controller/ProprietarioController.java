package com.br.transito.controller;

import com.br.transito.domain.model.Proprietario;
import com.br.transito.domain.model.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@AllArgsConstructor
@RestController
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;

    @GetMapping("/proprietarios ")
    public List<Proprietario> listar(){
    return proprietarioRepository.findAll();

    }
}
