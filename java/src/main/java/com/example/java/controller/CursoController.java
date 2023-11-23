package com.example.java.controller;

import com.example.java.model.Curso;
import com.example.java.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Endpoint para criar um curso
    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.createCurso(curso);
        return new ResponseEntity<>(novoCurso, HttpStatus.CREATED);
    }

    // Endpoint para buscar todos os cursos
    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = cursoService.getAllCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    // Endpoint para buscar um curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoService.getCursoById(id);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    // Endpoint para atualizar um curso por ID
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoAtualizado) {
        Curso curso = cursoService.updateCurso(id, cursoAtualizado);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    // Endpoint para excluir um curso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
