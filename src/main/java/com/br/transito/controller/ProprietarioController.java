package com.br.transito.controller;

import com.br.transito.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProprietarioController {

    @GetMapping("/proprietarios ")
    public List<Proprietario> listar(){

        var proprietario1 = new Proprietario();
        proprietario1.setId(1L);
        proprietario1.setNome("Paulo");
        proprietario1.setTelefone("11 977778888");
        proprietario1.setEmail("paulinduGrau@testPostman");

        var proprietario2 = new Proprietario();
        proprietario2.setId(2L);
        proprietario2.setNome("Claudio");
        proprietario2.setTelefone("11 955555555");
        proprietario2.setEmail("cLaudio123@testePostman");

        return Arrays.asList(proprietario1, proprietario2);
    }
}
