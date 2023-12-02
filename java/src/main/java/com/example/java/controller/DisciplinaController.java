package com.example.java.controller;

import com.example.java.model.Disciplina;
import com.example.java.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Disciplina> createDisciplina(@RequestBody Disciplina disciplina) {
        Disciplina novaDisciplina = disciplinaService.createDisciplina(disciplina);
        return new ResponseEntity<>(novaDisciplina, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Disciplina>> getAllDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.getAllDisciplinas();
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable Long id) {
        Disciplina disciplina = disciplinaService.getDisciplinaById(id);
        return new ResponseEntity<>(disciplina, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplinaAtualizada) {
        Disciplina disciplina = disciplinaService.updateDisciplina(id, disciplinaAtualizada);
        return new ResponseEntity<>(disciplina, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
        disciplinaService.deleteDisciplina(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
