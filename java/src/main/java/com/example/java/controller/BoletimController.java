package com.example.java.controller;

import com.example.java.model.Aluno;
import com.example.java.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boletim")
public class BoletimController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{alunoId}")
    public ResponseEntity<Aluno> getBoletim(@PathVariable Long alunoId) {
        // lógica para calcular e retornar o boletim
        Aluno aluno = alunoService.getAlunoById(alunoId);
        // realizar o cálculo do status aprovado/reprovado e outras lógicas necessárias
        return ResponseEntity.ok(aluno);
    }
}
